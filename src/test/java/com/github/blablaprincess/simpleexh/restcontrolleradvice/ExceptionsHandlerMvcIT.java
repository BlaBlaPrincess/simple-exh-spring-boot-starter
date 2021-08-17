package com.github.blablaprincess.simpleexh.restcontrolleradvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.blablaprincess.simpleexh.SimpleExhAutoConfiguration;
import com.github.blablaprincess.simpleexh.errordtobuilder.DefaultErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = SimpleExhAutoConfiguration.class)
class ExceptionsHandlerMvcIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TestConfig.Thrower thrower;

    @Test
    void test() throws Throwable {
        // Arrange
        String message = "Something wrong";
        Exception exception = new Exception(message);
        doThrow(exception).when(thrower).doWork();

        // Act
        String jsonResult = mvc.perform(MockMvcRequestBuilders.get(TestConfig.Controller.DO_STAFF_URI))
                .andExpect(status().isInternalServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DefaultErrorDto result = mapper.readValue(jsonResult, DefaultErrorDto.class);

        // Assert
        assertEquals(message, result.getMessage());
        assertEquals(exception.getClass().getSimpleName(), result.getError());
        assertEquals(TestConfig.Controller.DO_STAFF_URI, result.getPath());
        verify(thrower).doWork();
    }

    @TestConfiguration
    public static class TestConfig {

        public interface Thrower {
            void doWork() throws Throwable;
        }

        @Bean
        public Thrower thrower() {
            return mock(Thrower.class);
        }

        @RestController
        public static class Controller {
            final static String DO_STAFF_URI = "/throw";

            @Autowired
            private Thrower thrower;

            @GetMapping(DO_STAFF_URI)
            @ResponseStatus(OK)
            public void doStuff() throws Throwable {
                thrower.doWork();
            }
        }
    }
}