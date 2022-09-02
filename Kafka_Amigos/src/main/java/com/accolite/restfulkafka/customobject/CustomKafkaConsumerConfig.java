package com.accolite.restfulkafka.customobject;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class CustomKafkaConsumerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	public Map<String, Object> customConsumerConfig(){
		Map<String, Object> map=new HashMap<>();
		map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return map;
	}

	@Bean
	public ConsumerFactory< String, CustomMessage> customConsumerFactory(){
		JsonDeserializer<CustomMessage> valueDeserializer = new JsonDeserializer<>();
		valueDeserializer.addTrustedPackages("*");
		return new DefaultKafkaConsumerFactory<>(customConsumerConfig(),new StringDeserializer(),valueDeserializer);
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CustomMessage>> customKafkaListener(
			ConsumerFactory< String, CustomMessage> customConsumerFactory){
		ConcurrentKafkaListenerContainerFactory<String, CustomMessage> factory= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(customConsumerFactory);
		return factory;
		
	}
}
