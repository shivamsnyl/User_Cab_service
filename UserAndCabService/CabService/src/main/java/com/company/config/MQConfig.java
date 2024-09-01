package com.company.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {
	
	public static final String EXCH_NAME = "MQExch";
	public static final String QUEUE_NAME = "MQFirstQueue";
	public static final String KEY = "iloverabbitmq";

	/*
	 * @Bean public Queue queue() { return new Queue(MQConfig.QUEUE_NAME); }
	 * 
	 * @Bean public Exchange exchange() { return new
	 * DirectExchange(MQConfig.EXCH_NAME); }
	 * 
	 * @Bean public MessageConverter converter() { return new
	 * Jackson2JsonMessageConverter(); }
	 * 
	 * @Bean public Binding binding(Queue queue, Exchange exchange) { return
	 * BindingBuilder.bind(queue).to(exchange).with(MQConfig.KEY).noargs(); }
	 * 
	 * @Bean public AmqpTemplate template(ConnectionFactory factory) {
	 * RabbitTemplate template = new RabbitTemplate(factory);
	 * template.setMessageConverter(converter()); return template;
	 * 
	 * }
	 */
}
