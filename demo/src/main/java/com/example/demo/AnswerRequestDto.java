package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequestDto {
    private String content;
    private Long questionId;
}
