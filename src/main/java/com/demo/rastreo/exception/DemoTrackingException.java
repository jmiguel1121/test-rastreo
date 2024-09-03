package com.demo.rastreo.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@JsonIgnoreProperties({"stackTrace", "suppressed", "localizedMessage","cause"})
public class DemoTrackingException extends Exception {
    private HttpStatusCode status;
    private String message;
    private String trace;
    private String exception;

    public DemoTrackingException(HttpStatusCode code, String exception, String message, String trace) {
        super(message);
        this.exception = exception;
        this.message = message;
        this.status = code;
        this.trace = trace;
    }
}
