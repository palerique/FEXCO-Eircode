package br.com.sitedoph.fexco.postcode.web.rest;

import br.com.sitedoph.fexco.postcode.service.PostcodeService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing Address.
 */
@RestController
@RequestMapping("/api")
public class PostcodeResource {

    private final Logger log = LoggerFactory.getLogger(PostcodeResource.class);

    @Inject
    private PostcodeService postcodeService;

    /**
     * GET  /addresses : get all the addresses that have some postcodePart.
     *
     * @param postcodePart the postcode part information
     * @return the ResponseEntity with status 200 (OK) and the list of postcodes that contains the received postcode part
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/postcodes/{postcodePart}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<String> getPostcodesByPostcodePart(@PathVariable String postcodePart)
        throws URISyntaxException {
        log.debug("REST request to get a postcodes that contains : " + postcodePart);
        return postcodeService.findByPostcodeContains(postcodePart);
    }
}
