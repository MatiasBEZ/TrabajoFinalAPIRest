package com.trabajofinalinfo.apinoticias.config;

import com.trabajofinalinfo.apinoticias.exception.IdValueNotFoundException;
import com.trabajofinalinfo.apinoticias.exception.InvalidDateException;
import com.trabajofinalinfo.apinoticias.exception.InvalidIdException;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError();
        error.setStatus(status);
        error.setMessage("Validation error");
        error.setNumberOfErrors(ex.getErrorCount());
        List<ApiSubError> subErrors = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            subErrors.add(new ApiSubError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        error.setSubErrors(subErrors);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<Object> handleInvalidDateException(Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdValueNotFoundException.class)
    public ResponseEntity<Object> handleIdValueNotFoundException(Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("Source or Author ID cannot be null!");
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
            Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage("Author or Source ID doesn't exist!");
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<Object> handleTransientPropertyValueException(
            Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<Object> handleInvalidIdException(
            Exception ex, WebRequest request) {
        ApiRuntimeError error = new ApiRuntimeError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}
