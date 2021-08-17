package com.github.blablaprincess.simpleexh.errordtobuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DefaultErrorDtoBuilder implements ErrorDtoBuilder {
    @Override
    public DefaultErrorDto build(Throwable exception, HttpServletRequest request, HttpServletResponse response) {
        return DefaultErrorDto.builder()
                .path(request.getRequestURI())
                .error(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC))
                .build();
    }
}
