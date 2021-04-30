package com.orangeuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrangeUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeUserApplication.class, args);
	}

}
