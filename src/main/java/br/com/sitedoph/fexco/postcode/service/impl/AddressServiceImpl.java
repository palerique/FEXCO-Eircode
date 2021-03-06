package br.com.sitedoph.fexco.postcode.service.impl;

import br.com.sitedoph.fexco.postcode.domain.Address;
import br.com.sitedoph.fexco.postcode.repository.AddressRepository;
import br.com.sitedoph.fexco.postcode.service.AddressService;
import br.com.sitedoph.fexco.postcode.service.PostCoderThirdAPIWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing Address.
 */
@Service
public class AddressServiceImpl implements AddressService {

    public static final String ADDRESS_PAGES          = "address-pages";
    public static final String ADDRESS_POSTCODE_PAGES = "address-postcode-pages";
    public static final String NR14_7_PZ              = "NR14 7PZ";
    public static final String D02_X285               = "D02 X285";

    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Inject
    private AddressRepository addressRepository;

    @Inject
    private PostCoderThirdAPIWebService postCoderThirdAPIWebService;

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    @Caching(evict = {
        @CacheEvict(value = ADDRESS_PAGES, allEntries = true),
        @CacheEvict(value = ADDRESS_POSTCODE_PAGES, allEntries = true),
        @CacheEvict(value = "postcodes", allEntries = true)
    })
    public Address save(Address address) {
        log.debug("Request to save Address : {}", address);

        //TODO: add to cache!

        Address result = addressRepository.save(address);
        return result;
    }

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Cacheable(ADDRESS_PAGES)
    public Page<Address> findAll(Pageable pageable) {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll(pageable);
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
    @Caching(evict = {
        @CacheEvict(value = ADDRESS_PAGES, allEntries = true),
        @CacheEvict(value = ADDRESS_POSTCODE_PAGES, allEntries = true),
        @CacheEvict(value = "postcodes", allEntries = true)
    })
    public void delete(String id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.delete(id);
    }

    @Override
    @Caching(evict = {
        @CacheEvict(value = ADDRESS_PAGES, allEntries = true),
        @CacheEvict(value = ADDRESS_POSTCODE_PAGES, allEntries = true),
        @CacheEvict(value = "postcodes", allEntries = true)
    })
    public void saveAll(List<Address> addresses) {
        log.debug("Request to save all Addresses : {}", addresses);
        List<Address> toSave = new ArrayList<>();
        for (Address address : addresses) {
            Example<Address> example = Example.of(address);
            addressRepository.findAll(example);
            if (!addressRepository.exists(example)) {
                toSave.add(address);
            }
        }
        addressRepository.save(toSave);
    }

    @Override
    @Cacheable(ADDRESS_POSTCODE_PAGES)
    public Page<Address> findByPostcode(String postcode, Pageable pageable) {
        log.debug("Request to load page | {} | with Address that have the postcode | {} |", pageable, postcode);

        final Page<Address> inDatabase = addressRepository.findByPostcode(postcode, pageable);
        if (inDatabase.getTotalElements() > 0) {
            log.debug("returning addresses found in database | {} ", inDatabase);
            return inDatabase;
        }

        log.debug("no address in database matching this postcode | {} | searching in third-party API", postcode);
        List<Address> addressesInThirdPartyAPI = postCoderThirdAPIWebService.findByPostcode(postcode);
        if (!addressesInThirdPartyAPI.isEmpty()) {
            log.debug("addresses found in third-party API | {} ", addressesInThirdPartyAPI);

            final Address firstEncounteredAddress = addressesInThirdPartyAPI.get(0);
            final String  returnedPostcode        = firstEncounteredAddress.getPostcode();
            if (isTheDefaultUKResponse(postcode, firstEncounteredAddress, returnedPostcode)
                || isTheDefaultIEResponse(postcode, firstEncounteredAddress, returnedPostcode)) {
                log.debug("Third-party API returning the default response. Desired postcode | {} | returned postcode | {} | ", postcode, returnedPostcode);
                return new PageImpl<>(new ArrayList<>(), pageable, 0);
            }

            this.saveAll(addressesInThirdPartyAPI);
            return findByPostcode(postcode, pageable);
        } else {
            log.debug("no address found neither in the database nor in the third-party API returning empty page for postcode: {}", postcode);
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
    }

    private boolean isTheDefaultUKResponse(String postcode, Address firstEncounteredAddress, String returnedPostcode) {
        return NR14_7_PZ.equals(returnedPostcode) && !postcode.equals(NR14_7_PZ) && addressRepository.exists(Example.of(firstEncounteredAddress));
    }

    private boolean isTheDefaultIEResponse(String postcode, Address firstEncounteredAddress, String returnedPostcode) {
        return D02_X285.equals(returnedPostcode) && !postcode.equals(D02_X285) && addressRepository.exists(Example.of(firstEncounteredAddress));
    }
}
