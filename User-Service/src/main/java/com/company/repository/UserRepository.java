package com.company.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	

}
