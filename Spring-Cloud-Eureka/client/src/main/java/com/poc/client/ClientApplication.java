package com.poc.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ClientApplication {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@RequestMapping("/")
	public String callService() {
		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("app", false);
		String baseUrl = instanceInfo.getHomePageUrl();
		return restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
	}
}
