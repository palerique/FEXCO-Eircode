package br.com.sitedoph.fexco.postcode.service;

import java.util.List;

/**
 * Service Interface for managing Postcodes.
 */
public interface PostcodeService {

    /**
     * Get all postcodes containing this postcode part.
     *
     * @return the list of entities
     */
    List<String> findByPostcodeContains(String postcodePart);
}
