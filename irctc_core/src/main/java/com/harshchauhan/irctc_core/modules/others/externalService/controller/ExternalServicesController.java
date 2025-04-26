package com.harshchauhan.irctc_core.modules.others.externalService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshchauhan.irctc_core.modules.others.externalService.dto.PostsDto;
import com.harshchauhan.irctc_core.modules.others.externalService.service.ExternalPostsService;
import com.harshchauhan.irctc_core.utility.responseHandler.ResponseHandler;
import com.harshchauhan.irctc_core.utility.responseHandler.responseClasses.DataResponse;

@RestController
@RequestMapping("/external-services")
public class ExternalServicesController {

    private final ExternalPostsService externalPostsService;

    public ExternalServicesController(ExternalPostsService externalPostsService) {
        this.externalPostsService = externalPostsService;
    }

    @GetMapping("/v1/post")
    public ResponseEntity<DataResponse<PostsDto[]>> getAllPostsV1() {
        PostsDto[] getAllPostsResponse = externalPostsService.getAllPostsV1();

        return ResponseHandler.data(getAllPostsResponse, HttpStatus.OK);
    }

    @GetMapping("/v2/post")
    public ResponseEntity<DataResponse<PostsDto[]>> getAllPostsV2() {
        PostsDto[] getAllPostsResponse = externalPostsService.getAllPostsV2();

        return ResponseHandler.data(getAllPostsResponse, HttpStatus.OK);
    }

}
