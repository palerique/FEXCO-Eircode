package br.com.sitedoph.fexco.postcode.service.impl;

import br.com.sitedoph.fexco.postcode.domain.Address;
import br.com.sitedoph.fexco.postcode.service.PostCoderThirdAPIWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ph on 9/18/16.
 */
@Service
public class PostCoderThirdAPIWebServiceImpl implements PostCoderThirdAPIWebService {

    public static final String FORMAT_JSON    = "format=json";
    public static final String THREE_LINES    = "lines=3";
    public static final String METHOD         = "address";
    public static final String UK             = "uk";
    public static final String IE             = "ie";
    public static final String ONLY_POSTCODES = "postcodeonly=true";
    private final       Logger log            = LoggerFactory.getLogger(PostCoderThirdAPIWebServiceImpl.class);
    @Value("${postcoder.token}")
    private String       apiToken;
    @Inject
    private RestTemplate restTemplate;

    @Override
    public List<Address> findByPostcode(String postcode) {
        List<Address> ukAddresses = new ArrayList<>();
        try {
            ukAddresses = getAddressList(postcode, UK);
        } catch (UnsupportedEncodingException | RestClientException e) {
            dealWithException(e);
        }

        List<Address> ieAddresses = new ArrayList<>();
        try {
            ieAddresses = getAddressList(postcode, IE);
        } catch (UnsupportedEncodingException e) {
            dealWithException(e);
        }

        ieAddresses.addAll(ukAddresses);

        return ieAddresses;
    }

    private void dealWithException(Exception e) {
        log.error("failed to reach third party API", e);
    }

    private List<Address> getAddressList(String postcode, String country) throws UnsupportedEncodingException {

        //TODO: get when result has multi pages!!! example: CV4 7AL

        int page = 0;

        ResponseEntity<List<Address>> listResponseEntity = getListResponseEntity(getURL(postcode, country, page));

        if (checkCallFailure(listResponseEntity)) {
            return new ArrayList<>();
        } else {
            List<Address> result = new ArrayList<>(listResponseEntity.getBody());
            while (hasANextPage(listResponseEntity)) {
                ++page;
                listResponseEntity = getListResponseEntity(getURL(postcode, country, page));
                if (!checkCallFailure(listResponseEntity)) {
                    result.addAll(listResponseEntity.getBody());
                }
            }
            return result;
        }
    }

    private boolean hasANextPage(ResponseEntity<List<Address>> responseEntity) {
        return responseEntity != null && responseEntity.getBody().size() == 100 && responseEntity.getBody().get(99).getNextpage() != null;
    }

    private boolean checkCallFailure(ResponseEntity<List<Address>> rateResponse) {
        // check for call failure
        if (rateResponse == null) {
            return true;
        }
        if (rateResponse.getStatusCodeValue() != HttpURLConnection.HTTP_OK) {
            log.error("Failed : HTTP error code : " + rateResponse.getStatusCodeValue());
            return true;
        }
        return false;
    }

    private ResponseEntity<List<Address>> getListResponseEntity(String uri) {
        try {
            final ResponseEntity<List<Address>> exchange = restTemplate.exchange(uri,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Address>>() {
                });
            return exchange;
        } catch (HttpClientErrorException e) {
            //"Browser Direct Lookups exceeded, retry later"
            log.error(e.getResponseBodyAsString(), e);
            return null;
        }
    }

    private String getURL(String postcode, String country, int page) throws UnsupportedEncodingException {
        final String postcodeEncoded = postcode;

        //page	For use with searches that return more than 100 results; first page is 0 - see below
//        Paging
//
//        Up to 100 results can be returned at a time in response to a request.
//
//        If there are more results beyond the current page, the last result will include a morevalues field with a value of true.
//
//            We also include a nextpage field with the number of the next page, so you can easily append ?page=[nextpage] to your request.
//
//            Page 0 is the first page of results, page 1 is the second and so on.

        final String url = String.format("http://ws.postcoder.com/pcw/%s/%s/%s/%s?%s&%s&%s&page=%s",
            apiToken,
            METHOD,
            country,
            postcodeEncoded,
            FORMAT_JSON,
            THREE_LINES,
            ONLY_POSTCODES,
            page);

        log.debug("generated URL : " + url);

        return url;
    }
}
