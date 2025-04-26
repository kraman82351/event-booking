package com.harshchauhan.irctc_customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IrctcCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrctcCustomerApplication.class, args);
	}

}
