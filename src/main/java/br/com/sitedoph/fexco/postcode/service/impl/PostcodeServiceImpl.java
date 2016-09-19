package br.com.sitedoph.fexco.postcode.service.impl;

import br.com.sitedoph.fexco.postcode.domain.Address;
import br.com.sitedoph.fexco.postcode.repository.AddressRepository;
import br.com.sitedoph.fexco.postcode.service.PostcodeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ph on 9/18/16.
 */
@Service
public class PostcodeServiceImpl implements PostcodeService {

    public static final Pageable PAGE_REQUEST = new PageRequest(0, 10);

    @Inject
    private AddressRepository addressRepository;

    @Override
    @Cacheable("postcodes")
    public List<String> findByPostcodeContains(String postcodePart) {
        final Page<Address> addressesPage = addressRepository.findByPostcodeContains(postcodePart, PAGE_REQUEST);
        List<String> result = addressesPage.getContent()
            .stream().map(e -> e.getPostcode()).distinct().collect(Collectors.toList());
        return result;
    }
}
