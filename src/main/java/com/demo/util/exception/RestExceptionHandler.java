package com.demo.util.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler( value = {AppRequestException.class})
   public ResponseEntity<Object> handleApiRequestException(AppRequestException e){
       AppExceptionMsg app = new AppExceptionMsg(e.getMessage(),e.getHttpStatus(), ZonedDateTime.now(ZoneId.of("Z")));
       return new ResponseEntity<>(app, e.getHttpStatus());
   }

}