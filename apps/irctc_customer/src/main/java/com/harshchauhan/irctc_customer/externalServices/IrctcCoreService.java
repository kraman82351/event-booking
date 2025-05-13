package com.harshchauhan.irctc_customer.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "IRCTC-CORE")
public interface IrctcCoreService {

    @GetMapping("/train")
    Object getAllTrains(@RequestHeader("Authorization") String authorizationHeader);

}
