// CacheInspectionService.java
package com.harshchauhan.irctc_core.modules.others.cache.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {

    private final CacheManager cacheManager;

    public CacheInspectionService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Map<String, Object> getCacheContents(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Object nativeCache = cache.getNativeCache();
            if (nativeCache instanceof Map) {
                Map<String, Object> processedCache = processCacheMap((Map<?, ?>) nativeCache);
                return Collections.singletonMap(cacheName, processedCache);
            }
            return Collections.singletonMap(cacheName,
                    "Cache content not in Map format: " +
                            (nativeCache != null ? nativeCache.getClass().getSimpleName() : "null"));
        }
        return Collections.singletonMap(cacheName, "Cache not found");
    }

    public List<String> getAllCacheNames() {
        return StreamSupport.stream(cacheManager.getCacheNames().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getAllCacheContents() {
        return StreamSupport.stream(cacheManager.getCacheNames().spliterator(), false)
                .collect(Collectors.toMap(
                        cacheName -> cacheName,
                        cacheName -> {
                            Cache cache = cacheManager.getCache(cacheName);
                            if (cache != null) {
                                Object nativeCache = cache.getNativeCache();
                                if (nativeCache instanceof Map) {
                                    return processCacheMap((Map<?, ?>) nativeCache);
                                }
                                return "Cache content not in Map format: " +
                                        (nativeCache != null ? nativeCache.getClass().getSimpleName() : "null");
                            }
                            return "Cache not found";
                        }));
    }

    private Map<String, Object> processCacheMap(Map<?, ?> cacheMap) {
        Map<String, Object> result = new LinkedHashMap<>();
        cacheMap.forEach((key, value) -> {
            String stringKey = (key != null) ? key.toString() : "null";
            Object processedValue = (value instanceof org.springframework.cache.support.NullValue)
                    ? "[NULL_VALUE]"
                    : value;
            result.put(stringKey, processedValue);
        });
        return result;
    }
}