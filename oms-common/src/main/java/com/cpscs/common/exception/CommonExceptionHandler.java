package com.cpscs.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cpscs.common.utils.Result;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    Result exception(Exception e) {
        return Result.error(500, e.getMessage());
    }
}
