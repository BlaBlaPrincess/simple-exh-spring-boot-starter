package com.github.blablaprincess.simpleexh.restcontrolleradvice;

import com.github.blablaprincess.simpleexh.errordtobuilder.ErrorDtoBuilder;
import com.github.blablaprincess.simpleexh.exceptioncoderesolver.ExceptionCodeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandler {

    private final ExceptionCodeResolver exceptionCodeResolver;
    private final ErrorDtoBuilder dtoBuilder;

    @ExceptionHandler(Throwable.class)
    public Object handleException
            (Throwable exception, HttpServletRequest request, HttpServletResponse response) {

        int code = exceptionCodeResolver.resolve(exception);
        response.setStatus(code);

        return dtoBuilder.build(exception, request, response);
    }
}
