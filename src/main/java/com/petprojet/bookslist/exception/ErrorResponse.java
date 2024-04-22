package com.petprojet.bookslist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

public class ErrorResponse<T> extends ResponseEntity {
    public ErrorResponse(Object body, MultiValueMap<String, String> headers, String status) {
        super(body, headers,
              Arrays.stream(HttpStatus.values())
                    .filter(s -> s.name().equals(status))
                    .findAny()
                    .orElseThrow());
    }
}
