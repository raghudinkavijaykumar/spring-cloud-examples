package com.poc.secureservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication

@RestController
public class SecureserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureserviceApplication.class, args);
	}
	//added
	@Autowired
	private ResourceServerProperties sso;

	//added
	@Bean
	public ResourceServerTokenServices myUserInfoTokenServices() {
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}

	@PreAuthorize("#oauth2.hasScope('toll_read') and hasAuthority('ROLE_OPERATOR')")
	@RequestMapping("/tolldata")
	public ArrayList<TollUsage> getTollData() {

		TollUsage instance1 = new TollUsage("200", "station150", "B65GT1W", "2016-09-30T06:31:22");
		TollUsage instance2 = new TollUsage("201", "station119", "AHY673B", "2016-09-30T06:32:50");
		TollUsage instance3 = new TollUsage("202", "station150", "ZN2GP0", "2016-09-30T06:37:01");

		ArrayList<TollUsage> tolls = new ArrayList<TollUsage>();
		tolls.add(instance1);
		tolls.add(instance2);
		tolls.add(instance3);

		return tolls;
	}

	public class TollUsage {

		public String Id;
		public String stationId;
		public String licensePlate;
		public String timestamp;

		public TollUsage() {}

		public TollUsage(String id, String stationid, String licenseplate, String timestamp){
			this.Id = id;
			this.stationId = stationid;
			this.licensePlate = licenseplate;
			this.timestamp = timestamp;
		}


	}

}
