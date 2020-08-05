package com.rbailen.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rbailen.covid.model.Country;

/**
 * The Interface CountryRepository.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

  /**
   * Find by slug.
   *
   * @param slug the slug
   * @return the country
   */
  Country findBySlug(String slug);

}
