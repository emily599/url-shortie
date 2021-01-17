package com.doan.urlshortie.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UrlControllerTests {
    @Autowired
    private UrlController urlController;

    private static final String URL = "https://dolly.com/about/";
    private static final String BLANK_URL = "";
    private static final String INVALID_URL = "dolly";
    private static final String ID = "d3cccd90";
    private static final String INVALID_ID = "xxxxxxxx";


    @Test
    public void testUrlReturnsId() {
        final ResponseEntity idResponseFromUrl = urlController.createIdFromUrl(URL);

        assertEquals(HttpStatus.OK, idResponseFromUrl.getStatusCode());
        assertEquals("Shortened URL:" + ID, idResponseFromUrl.getBody());
    }

    @Test
    void testBlankUrl() {
        final ResponseEntity idResponseFromUrl = urlController.createIdFromUrl(BLANK_URL);

        assertEquals(HttpStatus.BAD_REQUEST, idResponseFromUrl.getStatusCode());
    }

    @Test
    void testInvalidUrl() {
        final ResponseEntity idResponseFromUrl = urlController.createIdFromUrl(INVALID_URL);

        assertEquals(HttpStatus.BAD_REQUEST, idResponseFromUrl.getStatusCode());
    }

    @Test
    void testIdReturnsUrl() {
        final ResponseEntity urlResponseFromUrl = urlController.getUrlFromId(ID);

        assertEquals(HttpStatus.OK, urlResponseFromUrl.getStatusCode());
    }

    @Test
    void testInvalidId() {
        final ResponseEntity urlResponseFromUrl = urlController.getUrlFromId(INVALID_ID);

        assertEquals(HttpStatus.NOT_FOUND, urlResponseFromUrl.getStatusCode());
    }
}
