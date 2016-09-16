package br.com.sitedoph.fexco.postcode.repository;

import br.com.sitedoph.fexco.postcode.domain.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Authority entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}
