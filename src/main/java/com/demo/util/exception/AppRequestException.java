package com.demo.util.exception;

import org.springframework.http.HttpStatus;

public class AppRequestException extends RuntimeException{

    private HttpStatus httpStatus;

    public AppRequestException() {
    }

    public AppRequestException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public AppRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRequestException(Throwable cause) {
        super(cause);
    }

    public AppRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
