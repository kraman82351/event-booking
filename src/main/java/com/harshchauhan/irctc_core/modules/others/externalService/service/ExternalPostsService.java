package com.harshchauhan.irctc_core.modules.others.externalService.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.harshchauhan.irctc_core.modules.others.externalService.dto.PostsDto;

import reactor.util.retry.Retry;

@Service
public class ExternalPostsService {
    @Value("${external.api.posts.url}")
    private String postsApiUrl;

    RestTemplate restTemplate;
    WebClient webClient;

    public ExternalPostsService(RestTemplate restTemplate, @Qualifier("postsWebClient") WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    public PostsDto[] getAllPostsV1() {
        ResponseEntity<PostsDto[]> getAllPostsResponseEntity = restTemplate.exchange(postsApiUrl + "/posts",
                HttpMethod.GET, null,
                PostsDto[].class);

        PostsDto[] getAllPostsResponse = getAllPostsResponseEntity.getBody();

        return getAllPostsResponse;
    }

    public PostsDto[] getAllPostsV2() {
        PostsDto[] getAllPostsResponseEntity = webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(PostsDto[].class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(1)) // starts with 1s, doubles each time: 1s → 2s → 4s
                                .maxBackoff(Duration.ofSeconds(10)) // maximum wait
                                .jitter(0.3) // optional: adds randomness (30%)
                ).block();

        return getAllPostsResponseEntity;
    }

}
