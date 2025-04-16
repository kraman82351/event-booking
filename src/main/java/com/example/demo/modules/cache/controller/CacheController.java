// CacheController.java
package com.example.demo.modules.cache.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.cache.service.CacheInspectionService;
import com.example.demo.utility.responseHandler.ResponseHandler;
import com.example.demo.utility.responseHandler.responseClasses.DataResponse;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final CacheInspectionService cacheInspectionService;

    public CacheController(CacheInspectionService cacheInspectionService) {
        this.cacheInspectionService = cacheInspectionService;
    }

    @GetMapping()
    public ResponseEntity<DataResponse<Map<String, Object>>> getAllCaches() {
        return ResponseHandler.data(cacheInspectionService.getAllCacheContents(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<DataResponse<Iterable<String>>> getAllCacheNames() {
        return ResponseHandler.data(cacheInspectionService.getAllCacheNames(), HttpStatus.OK);
    }
    
    @GetMapping("/{cacheName}")
    public ResponseEntity<DataResponse<Map<String, Object>>> getCacheByName(@PathVariable String cacheName) {
        return ResponseHandler.data(cacheInspectionService.getCacheContents(cacheName), HttpStatus.OK);
    }
}