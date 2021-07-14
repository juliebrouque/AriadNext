package com.ariadnext.souscription;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="com.ariadnext.souscription")
public class CustomProperties {
	
	private String depo;
	
	private String api;
	
	private String username;
	
	private String password;

	public String getDepo() {
		return depo;
	}

	public void setDepo(String depo) {
		this.depo = depo;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
