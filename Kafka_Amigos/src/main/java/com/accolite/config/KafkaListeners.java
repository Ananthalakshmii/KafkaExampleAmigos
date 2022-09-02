package com.accolite.config;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaListeners {
	
	@KafkaListener(topics = "KafkaExample", groupId = "groupId")
	void consume(String data) {
		System.out.println("Listener received "+data);
	}

}
