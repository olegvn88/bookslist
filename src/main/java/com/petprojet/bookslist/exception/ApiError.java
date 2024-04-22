package com.petprojet.bookslist.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private int code;
    private String status;
    private String message;
}
