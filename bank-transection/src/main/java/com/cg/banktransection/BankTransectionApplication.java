package com.cg.banktransection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankTransectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTransectionApplication.class, args);
	}

	@Bean  //java configuration
	public RestTemplate getTemplate() {
		return new RestTemplate();    //RestTemplate obj = new RestTemplate();
	}
	
}

