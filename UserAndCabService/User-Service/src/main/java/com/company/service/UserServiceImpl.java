package com.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.config.MQConfig;
import com.company.dto.Cab;
import com.company.entities.OrderStatus;
import com.company.entities.User;
import com.company.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	@Autowired CabServiceFeign feign;
	
	public static List<Cab> cabs = new ArrayList<>();
	 
	
	 @RabbitListener(queues = MQConfig.QUEUE_NAME) 
	  public void receiveMessage(OrderStatus orderStatus) 
	    {  
	        //feign.getAllCabs();
	        cabs=orderStatus.getCabs();
	    } 

	@Override
	public User findUserById(int id) {
		//cabs = extracted();
		User user = new User();
		if (repository.findById(id).isPresent()) {
			user = repository.findById(id).get();
			user.setCabs(cabs);
		}
		return user != null ? user : null;
	}

	@Override
	public List<User> findAllUser() {
		//cabs = extracted();
		for (User u : (List<User>) repository.findAll()) {
			u.setCabs(cabs);
		}
		return (List<User>) repository.findAll();
	}

	@Override
	public void saveAll(List<User> users) {
		repository.saveAll(users);

	}
	
	private List<Cab> extracted() {
		List<Cab> cabs = feign.getAllCabs();
		return cabs;
	}
	

}
