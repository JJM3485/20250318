package com.example.demo;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")  // 기본 URL 설정
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    // 모든 질문 가져오기
    @GetMapping
    public ResponseEntity<List<QuestionDtoResponse>> getAllQuestions() {
        List<QuestionDtoResponse> questiondtos=questionService.getAllQuestions();
        return ResponseEntity.ok(questiondtos);
    }

    // 특정 질문 가져오기
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    //TODO : QuestionCreateRequestDTO 만들기기
    // 질문 생성 (POST 요청)
    @PostMapping
    public Question createQuestion(@RequestBody Question request) {
        return questionService.createQuestion(request.getSubject(), request.getContent());
    }

    //TODO: QuestionUpdateRequestDto 만들기기
    // 질문 수정 (PUT 요청)
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question request) {
        return questionService.updateQuestion(id, request.getSubject(), request.getContent());
    }

    // 질문 삭제 (DELETE 요청)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();

    }

    
}
