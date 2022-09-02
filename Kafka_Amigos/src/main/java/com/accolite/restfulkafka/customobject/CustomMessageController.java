package com.accolite.restfulkafka.customobject;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/custom")
public class CustomMessageController {
	@Autowired
	private KafkaTemplate<String, CustomMessage> kafkaTemplate;
	
	@PostMapping
	public void publishCustomMessage(@RequestBody CustomMessage message) {
		CustomMessage customMessage=new CustomMessage(message.data(), LocalDateTime.now());
		kafkaTemplate.send("KafkaExample",customMessage);
	}

}
