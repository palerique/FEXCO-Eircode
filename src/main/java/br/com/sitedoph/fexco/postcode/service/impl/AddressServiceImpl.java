package br.com.sitedoph.fexco.postcode.service.impl;

import br.com.sitedoph.fexco.postcode.domain.Address;
import br.com.sitedoph.fexco.postcode.repository.AddressRepository;
import br.com.sitedoph.fexco.postcode.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Address.
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Inject
    private AddressRepository addressRepository;

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    public Address save(Address address) {
        log.debug("Request to save Address : {}", address);
        Address result = addressRepository.save(address);
        return result;
    }

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Address> findAll(Pageable pageable) {

        //TODO: testing REST service consuming!
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<Address>> rateResponse =
//            restTemplate.exchange("http://ws.postcoder.com/pcw/PCW45-12345-12345-1234X/address/uk/NR147PZ?format=json&lines=3",
//                                  HttpMethod.GET, null, new ParameterizedTypeReference<List<Address>>() {
//                });
//        List<Address> addresses = rateResponse.getBody();
//
//        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        log.info(addresses.toString());
//        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//
//        this.saveAll(addresses);

        log.debug("Request to get all Addresses");
        Page<Address> result = addressRepository.findAll(pageable);
        return result;
    }

    /**
     * Get one address by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Address findOne(String id) {
        log.debug("Request to get Address : {}", id);
        Address address = addressRepository.findOne(id);
        return address;
    }

    /**
     * Delete the  address by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.delete(id);
    }

    @Override
    public void saveAll(List<Address> addresses) {
        addressRepository.save(addresses);
    }
}
