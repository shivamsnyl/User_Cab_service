package com.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.entities.Cab;

@Repository
public interface CabRepository extends CrudRepository<Cab, Integer> {

}
