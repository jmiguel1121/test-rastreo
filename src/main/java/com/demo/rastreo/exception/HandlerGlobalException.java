package com.demo.rastreo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HandlerGlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Error: ", ex);
        var resp = validKindError(ex);
        return new ResponseEntity<>(resp, resp.getStatus());
    }

    private DemoTrackingException validKindError(Exception ex) {
        if (ex instanceof DemoTrackingException) {
            return (DemoTrackingException) ex;
        } else {
            return new DemoTrackingException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getClass().getSimpleName(),
                    ex.getMessage(), ex.getStackTrace()[0].toString());
        }
    }
}
