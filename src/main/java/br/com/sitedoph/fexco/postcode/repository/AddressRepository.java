package br.com.sitedoph.fexco.postcode.repository;

import br.com.sitedoph.fexco.postcode.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Address entity.
 */
@SuppressWarnings("unused")
public interface AddressRepository extends MongoRepository<Address, String> {

}
