package com.joel.sprint.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApibrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApibrmApplication.class, args);
	}

}
