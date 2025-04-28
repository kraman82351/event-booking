package com.harshchauhan.irctc_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class IrctcConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrctcConfigServerApplication.class, args);
	}

}
