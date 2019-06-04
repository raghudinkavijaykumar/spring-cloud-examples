package com.poc.ribbontimeapp;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class RibbonTimeAppApplication {

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeAppApplication.class, args);
	}

	@GetMapping
	public String getRequest() {
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
