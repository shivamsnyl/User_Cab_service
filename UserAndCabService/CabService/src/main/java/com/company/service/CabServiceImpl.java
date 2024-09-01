package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.company.entities.Cab;
import com.company.repository.CabRepository;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	CabRepository repository;
	
	 /*@Autowired 
	 private RabbitTemplate rabbitTemplate;*/ 
	 
	 @Autowired
	 KafkaTemplate<String, Object> ktemplate;
	
	@Override
	public Cab getCabById(int id) {
		ktemplate.send("kafka-topic", repository.findById(id).get());
		return repository.findById(id).isPresent()? repository.findById(id).get():null;
	}

	@Override
	public List<Cab> getAllCabs() {
		
		/*List<Cab> allCabs = (List<Cab>)repository.findAll();
		
		OrderStatus status = new OrderStatus(allCabs, "Process", "All available cars are fetched and processed.");
		
		rabbitTemplate.convertAndSend(MQConfig.EXCH_NAME, MQConfig.KEY, status); */
		List<Cab> findAll = (List<Cab>) repository.findAll();
		ktemplate.send("kafka-topic-all", findAll);
		return (List<Cab>) findAll;
	}

	@Override
	public void saveAll(List<Cab> cab) {
		repository.saveAll(cab);
	}
	
	

}
