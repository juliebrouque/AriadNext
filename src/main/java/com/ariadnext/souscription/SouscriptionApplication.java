package com.ariadnext.souscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.social.config.annotation.EnableSocial;

@SpringBootApplication
@EnableSocial
public class SouscriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SouscriptionApplication.class, args);
	}

}
