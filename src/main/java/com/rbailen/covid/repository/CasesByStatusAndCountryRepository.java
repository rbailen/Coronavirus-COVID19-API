package com.rbailen.covid.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rbailen.covid.model.CasesByStatusAndCountry;

/**
 * The Interface CasesByStatusAndCountryRepository.
 */
@Repository
public interface CasesByStatusAndCountryRepository
    extends CrudRepository<CasesByStatusAndCountry, String> {

}
