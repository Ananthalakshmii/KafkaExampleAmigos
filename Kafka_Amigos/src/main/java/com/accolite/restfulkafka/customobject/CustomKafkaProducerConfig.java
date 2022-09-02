package com.accolite.restfulkafka.customobject;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class CustomKafkaProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	public Map<String, Object> customProducerConfig(){
		Map<String, Object> map=new HashMap<>();
		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return map;
	}
	
	@Bean
	public ProducerFactory<String, CustomMessage> customProducerFactory(){
		return new DefaultKafkaProducerFactory<>(customProducerConfig());
	}
	
	@Bean
	public KafkaTemplate<String , CustomMessage> customKafkaTemplate(ProducerFactory<String, CustomMessage> customProducerFactory){
		return new KafkaTemplate<>(customProducerFactory);
	}
}
