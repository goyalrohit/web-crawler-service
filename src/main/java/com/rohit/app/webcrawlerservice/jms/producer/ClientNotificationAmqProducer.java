package com.rohit.app.webcrawlerservice.jms.producer;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import java.util.UUID;

@Log4j2
@Component
public class ClientNotificationAmqProducer {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${activemq.destination.subscriber.crawler}")
	private String topic;

	public void send(String message) {
		log.info("Sending response to " + topic + " " + message + " ");
		jmsTemplate.convertAndSend(topic, message, msg -> {
            msg.setJMSCorrelationID(UUID.randomUUID().toString());
            return msg;
        });
	}
}