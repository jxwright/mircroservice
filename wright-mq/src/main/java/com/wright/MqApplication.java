package com.wright;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Source.class)
public class MqApplication {
	public static void main(String[] args) {
		SpringApplication.run(MqApplication.class, args);
	}
}
