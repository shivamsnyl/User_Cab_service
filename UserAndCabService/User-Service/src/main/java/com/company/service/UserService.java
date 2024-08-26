package com.company.service;

import java.util.List;

import com.company.entities.User;

public interface UserService {
	
	User findUserById(int id);
	List<User> findAllUser();
	void saveAll(List<User> users);

}
