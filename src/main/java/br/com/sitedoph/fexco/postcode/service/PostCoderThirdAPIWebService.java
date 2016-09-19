package br.com.sitedoph.fexco.postcode.service;

import br.com.sitedoph.fexco.postcode.domain.Address;

import java.util.List;

/**
 * Service Interface for querying PostCoder Web Service.
 */
public interface PostCoderThirdAPIWebService {

    List<Address> findByPostcode(String postcode);
}
