package com.example.demo;

import java.time.LocalDateTime;

import org.hibernate.internal.build.AllowPrintStacktrace;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDtoResponse {
    private Long id; 
    
    private String subject; 
 
    private String content; 

    private LocalDateTime createDate; 
    
    public static QuestionDtoResponse FromEntity(Question question) {
        return QuestionDtoResponse.builder()
        .id(question.getId())
        .subject(question.getSubject())
        .content(question.getContent())
        .createDate(question.getCreateDate())     
        .build();
    }

    
}
