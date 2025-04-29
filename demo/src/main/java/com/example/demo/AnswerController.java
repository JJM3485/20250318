package com.example.demo;

import com.example.demo.AnswerRequestDto; // DTO import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/answers")
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // 특정 질문에 대한 모든 답변 가져오기
    @GetMapping("/{questionId}")
    public ResponseEntity<List<Answer>> getAnswers(@PathVariable Long questionId) {
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

    // 답변 추가 (POST)
    @PostMapping
    public ResponseEntity<Answer> addAnswer(@RequestBody AnswerRequestDto request) {
        Answer answer = answerService.createAnswer(request.getQuestionId(), request.getContent());
        return ResponseEntity.ok(answer);
    }

    // 답변 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody AnswerRequestDto request) {
        Answer updatedAnswer = answerService.updateAnswer(id, request.getContent());
        return ResponseEntity.ok(updatedAnswer);
    }

    // 답변 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
