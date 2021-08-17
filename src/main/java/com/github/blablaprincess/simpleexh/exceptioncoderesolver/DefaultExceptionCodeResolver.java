package com.github.blablaprincess.simpleexh.exceptioncoderesolver;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

public class DefaultExceptionCodeResolver implements ExceptionCodeResolver {

    private final Map<Class<? extends Throwable>, Integer> exceptionCodeMap
            = new HashMap<Class<? extends Throwable>, Integer>(){{
        put(HttpRequestMethodNotSupportedException.class,  405);
        put(HttpMediaTypeNotSupportedException.class,      415);
        put(HttpMediaTypeNotAcceptableException.class,     406);
        put(MissingPathVariableException.class,            500);
        put(MissingServletRequestParameterException.class, 400);
        put(ServletRequestBindingException.class,          400);
        put(ConversionNotSupportedException.class,         500);
        put(TypeMismatchException.class,                   400);
        put(HttpMessageNotReadableException.class,         400);
        put(HttpMessageNotWritableException.class,         500);
        put(MethodArgumentNotValidException.class,         400);
        put(MissingServletRequestPartException.class,      400);
        put(BindException.class,                           400);
        put(NoHandlerFoundException.class,                 404);
        put(AsyncRequestTimeoutException.class,            503);
    }};

    @Override
    public int resolve(Throwable throwable) {
        return exceptionCodeMap.getOrDefault(throwable.getClass(), 500);
    }
}
