package com.harshchauhan.irctc_customer.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@Component
public class MongoDbAuthChecker implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Try a very lightweight command (ping)
            mongoTemplate.executeCommand("{ ping: 1 }");
            System.out.println("✅ MongoDB authentication successful.");
        } catch (Exception e) {
            System.err.println("❌ MongoDB authentication failed: " + e.getMessage());
            throw e; // Force Spring Boot to fail startup
        }
    }
}
