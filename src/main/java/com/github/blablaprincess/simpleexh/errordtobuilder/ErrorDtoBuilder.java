package com.github.blablaprincess.simpleexh.errordtobuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ErrorDtoBuilder {
    Object build(Throwable exception, HttpServletRequest request, HttpServletResponse response);
}
