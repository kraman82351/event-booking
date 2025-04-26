package com.harshchauhan.irctc_customer.temp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/temp/health")
public class TempController {

    private final WebClient.Builder webClientBuilder;

    public TempController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping()
    public ResponseEntity<Object> otherHealth() {
        Object temp = webClientBuilder.build().get().uri("http://irctc-core/api/v1/actuator/health").retrieve()
                .bodyToMono(Object.class).block();

        return ResponseEntity.ok(temp);
    }

    @GetMapping("hi")
    public ResponseEntity<String> temp() {
        return ResponseEntity.ok("HI");
    }
}
