package com.poc.secureauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@SpringBootApplication
@EnableAuthorizationServer
public class SecureauthserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureauthserverApplication.class, args);
	}

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
