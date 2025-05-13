package com.harshchauhan.irctc_customer.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableFeignClients(basePackages = "com.harshchauhan.irctc_customer.externalServices")
@EnableAspectJAutoProxy
public class ApplicationConfig {

}
