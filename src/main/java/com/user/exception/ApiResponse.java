package com.user.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    private String msg;
    private boolean success;
    private HttpStatus httpStatus;

}
