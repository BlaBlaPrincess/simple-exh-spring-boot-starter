package com.github.blablaprincess.simpleexh.errordtobuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultErrorDto {
    private String path;
    private String error;
    private String message;
    private LocalDateTime timestamp;
}
