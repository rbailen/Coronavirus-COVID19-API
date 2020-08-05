package com.rbailen.covid.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rbailen.covid.model.Global;

/**
 * The Interface GlobalRepository.
 */
@Repository
public interface GlobalRepository extends CrudRepository<Global, String> {

}
