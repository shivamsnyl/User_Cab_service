package com.company.service;

import java.util.List;

import com.company.entities.MailDetail;
import com.company.entities.User;

public interface UserService {
	
	User findUserById(int id);
	List<User> findAllUser();
	void saveAll(List<User> users);

	//Mail services
	String sendMail(MailDetail mailDetail);
	String sendMailWithAttachment(MailDetail mailDetail);
	
}
