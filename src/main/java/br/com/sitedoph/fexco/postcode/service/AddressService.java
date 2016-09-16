package br.com.sitedoph.fexco.postcode.service;

import br.com.sitedoph.fexco.postcode.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Address.
 */
public interface AddressService {

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    Address save(Address address);

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Address> findAll(Pageable pageable);

    /**
     * Get the "id" address.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Address findOne(String id);

    /**
     * Delete the "id" address.
     *
     * @param id the id of the entity
     */
    void delete(String id);

    void saveAll(List<Address> addresses);
}
