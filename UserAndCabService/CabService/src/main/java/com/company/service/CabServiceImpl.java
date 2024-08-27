package com.company.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.config.MQConfig;
import com.company.entities.Cab;
import com.company.entities.OrderStatus;
import com.company.repository.CabRepository;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	CabRepository repository;
	
	 @Autowired 
	 private RabbitTemplate rabbitTemplate; 
	
	@Override
	public Cab getCabById(int id) {
		return repository.findById(id).isPresent()? repository.findById(id).get():null;
	}

	@Override
	public List<Cab> getAllCabs() {
		
		List<Cab> allCabs = (List<Cab>)repository.findAll();
		
		OrderStatus status = new OrderStatus(allCabs, "Process", "All available cars are fetched and processed.");
		
		rabbitTemplate.convertAndSend(MQConfig.EXCH_NAME, MQConfig.KEY, status);
		return (List<Cab>) repository.findAll();
	}

	@Override
	public void saveAll(List<Cab> cab) {
		repository.saveAll(cab);
	}
	
	

}
