package com.doan.urlshortie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class UrlShortieApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortieApplication.class, args);
    }
}
