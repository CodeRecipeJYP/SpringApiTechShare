package com.navercorp.bookserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.AdviceTrait;

@ControllerAdvice
public class GlobalExceptionHandler implements AdviceTrait {
    @ExceptionHandler
    public ResponseEntity<Problem> handleNullPointerException (
            NullPointerException exception,
            NativeWebRequest request) throws HttpMediaTypeNotAcceptableException {
        return create(Status.NOT_FOUND, exception, request);
    }
}
