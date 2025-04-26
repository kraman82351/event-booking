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
        System.out.println("HEREEEE");
        Object temp = webClientBuilder.build().get().uri("http://irctc-core/api/v1/actuator/health").retrieve()
                .bodyToMono(Object.class).block();

        return ResponseEntity.ok(temp);
    }

    @GetMapping("hi")
    public ResponseEntity<String> temp() {
        return ResponseEntity.ok("HI");
    }

    // PostsDto[] getAllPostsResponseEntity = webClient
    // .get()
    // .uri("/posts")
    // .retrieve()
    // .bodyToMono(PostsDto[].class)
    // .retryWhen(
    // Retry.backoff(3, Duration.ofSeconds(1)) // starts with 1s, doubles each time:
    // 1s → 2s → 4s
    // .maxBackoff(Duration.ofSeconds(10)) // maximum wait
    // .jitter(0.3) // optional: adds randomness (30%)
    // ).block();
}
