package com.accolite.restfulkafka.customobject;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomKafkaListenerClass {
	
	@KafkaListener(topics = "KafkaExample", groupId = "groupId")
	public void customKafkaListen(CustomMessage customMessage) {
		System.out.println("Listener received "+customMessage);
	}

}
