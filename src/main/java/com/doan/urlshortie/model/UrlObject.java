package com.doan.urlshortie.model;

import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

@Getter
@AllArgsConstructor
public class UrlObject {

    private final String id;
    private final String url;

    public static UrlObject createIdFromUrl(final String url) {
        final String id = Hashing
                .murmur3_32()
                .hashString(url, StandardCharsets.UTF_8)
                .toString();

        return new UrlObject(id, url);
    }
}
