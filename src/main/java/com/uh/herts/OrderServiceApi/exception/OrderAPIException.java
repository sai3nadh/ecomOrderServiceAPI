package com.uh.herts.OrderServiceApi.exception;

import org.springframework.http.HttpStatus;

public class OrderAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public OrderAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public OrderAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
