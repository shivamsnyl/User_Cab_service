package com.company.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.company.entities.Cab;
import com.company.entities.MailDetail;
import com.company.entities.User;
import com.company.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	@Autowired CabServiceFeign feign;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String sender;
	
	//Cab c = null;
	//List<Cab> cabList = new ArrayList<>();
	
	public static List<Cab> cabs = new ArrayList<>();
	public static Cab cab = new Cab();
	 
	
	/*
	 * @RabbitListener(queues = MQConfig.QUEUE_NAME) public void
	 * receiveMessage(OrderStatus orderStatus) { //feign.getAllCabs();
	 * cabs=orderStatus.getCabs(); }
	 */
	 
	 @KafkaListener(topics = "kafka-topic", groupId = "group-1")
	 void getCab(Cab c1) {
		 UserServiceImpl.cab=c1;
	 }
	 
	 @KafkaListener(topics = "kafka-topic-all", groupId = "group-all")
	 void getCabs(List<Cab> c1) {
		 UserServiceImpl.cabs = c1;
	 }

	@Override
	public User findUserById(int id) {
		//cabs = extracted();
		User user = new User();
		if (repository.findById(id).isPresent()) {
			user = repository.findById(id).get();
			//user.setCabs(cabs);
			user.setCabs(cabs);
		}
		return user != null ? user : null;
	}

	@Override
	public List<User> findAllUser() {
		List<User> users = (List<User>) repository.findAll();
		for (User u : users) {
			u.setCabs(cabs);
		}
		
		MailDetail md = new MailDetail();
		md.setRecipient("shivamsnyal@gmail.com");
		md.setSubject("Cabs Fetched successfully!!!");
		md.setMsgBody(users.toString());
		System.out.println("==========" + md);
		sendMail(md);
		
		return users;
	}

	@Override
	public void saveAll(List<User> users) {
		repository.saveAll(users);

	}
	
	private List<Cab> extracted() {
		List<Cab> cabs = feign.getAllCabs();
		return cabs;
	}

	@Override
	public String sendMail(MailDetail mailDetail) {
		try {

			// Creating a simple mail message object
			SimpleMailMessage emailMessage = new SimpleMailMessage();

			// Setting up necessary details of mail
			emailMessage.setFrom(sender);
			emailMessage.setTo(mailDetail.getRecipient());
			emailMessage.setSubject(mailDetail.getSubject());
			emailMessage.setText(mailDetail.getMsgBody());

			// Sending the email
			mailSender.send(emailMessage);
			return "Email has been sent successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending email!!!";
		}

	}

	@Override
	public String sendMailWithAttachment(MailDetail mailDetail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachment to be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(mailDetail.getRecipient());
			mimeMessageHelper.setSubject(mailDetail.getSubject());
			mimeMessageHelper.setText(mailDetail.getMsgBody());

			// Adding the file attachment
			FileSystemResource file = new FileSystemResource(new File(mailDetail.getAttachment()));

			mimeMessageHelper.addAttachment(file.getFilename(), file);

			// Sending the email with attachment
			mailSender.send(mimeMessage);
			return "Email has been sent successfully...";
		}

		// Catch block to handle the MessagingException
		catch (MessagingException e) {

			// Display message when exception is occurred
			return "Error while sending email!!!";
		}
	}
	

}
