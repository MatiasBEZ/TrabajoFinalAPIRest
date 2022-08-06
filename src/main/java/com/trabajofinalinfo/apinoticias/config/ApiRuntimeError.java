package com.trabajofinalinfo.apinoticias.config;

import org.springframework.http.HttpStatus;

public class ApiRuntimeError {
    private HttpStatus status;
    private String message;

    public ApiRuntimeError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiRuntimeError() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
