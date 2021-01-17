package com.doan.urlshortie.controller;

import com.doan.urlshortie.model.UrlObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/url")
public class UrlController {

    @Autowired
    private RedisTemplate<String, UrlObject> redisTemplate;

    @Value("${redis.ttl}")
    private long ttl;

    @PostMapping
    public ResponseEntity createIdFromUrl(@RequestBody final String url) {

        final UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https"});

        if(!urlValidator.isValid(url)) {
            return ResponseEntity.badRequest().body("Invalid URL");
        }
        final UrlObject newUrlObject = UrlObject.createIdFromUrl(url);
        log.info("The URL id is {}", newUrlObject);
        redisTemplate.opsForValue().set(newUrlObject.getId(), newUrlObject, ttl, TimeUnit.SECONDS);
        return ResponseEntity.ok(newUrlObject.getId());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUrlFromId(@PathVariable final String id) {

        final UrlObject urlObject = redisTemplate.opsForValue().get(id);

        if (Objects.isNull(urlObject)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id does not exist.");
        }
        return ResponseEntity.ok(urlObject);
    }
}
