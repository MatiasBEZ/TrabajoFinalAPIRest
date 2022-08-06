package com.trabajofinalinfo.apinoticias.config;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {
    private HttpStatus status;
    private String message;
    private int numberOfErrors;
    private List<ApiSubError> subErrors;

    public ApiError(HttpStatus status, String message, int cantidadDeErrores, List<ApiSubError> subErrors) {
        this.status = status;
        this.message = message;
        this.numberOfErrors = cantidadDeErrores;
        this.subErrors = subErrors;
    }

    public ApiError() {
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

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(int numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<ApiSubError> subErrors) {
        this.subErrors = subErrors;
    }
}
