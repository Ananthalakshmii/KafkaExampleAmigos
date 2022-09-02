package com.accolite;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import com.accolite.restfulkafka.customobject.CustomMessage;

@SpringBootApplication
public class KafkaAmigosApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAmigosApplication.class, args);
	}

	/*
	@Bean //kafka- can handle millions of data
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args->{
			for(int i=0;i<10000;i++)
				kafkaTemplate.send("KafkaExample", "hello kafka :) "+i); //topic name should match with in kafkaTopicExchange
		};
	}*/
	
	//for custom message:
	
	CommandLineRunner commandLineRunner(KafkaTemplate<String, CustomMessage> kafkaTemplate) {
		return args ->{
			for(int i=0;i<1000;i++)
				kafkaTemplate.send("KafkaExample",new CustomMessage("hello kafka " +i, LocalDateTime.now()));
		};
	}
}
