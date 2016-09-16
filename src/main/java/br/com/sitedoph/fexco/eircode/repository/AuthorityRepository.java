package br.com.sitedoph.fexco.eircode.repository;

import br.com.sitedoph.fexco.eircode.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
