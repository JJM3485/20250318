package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    //TODO: DTo 하기기
    // ✅ 모든 질문 가져오기
    public List<QuestionDtoResponse> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(QuestionDtoResponse::FromEntity)
                .collect(Collectors.toList());
    }

    // ✅ ID로 특정 질문 가져오기
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    // ✅ 질문 생성 (Create)
    public Question createQuestion(String subject, String content) {
        Question newQuestion = new Question();
        newQuestion.setSubject(subject);
        newQuestion.setContent(content);
        return questionRepository.save(newQuestion);
    }

    // ✅ 질문 수정 (Update)
    @Transactional
    public Question updateQuestion(Long id, String subject, String content) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        question.setSubject(subject);
        question.setContent(content);
        return questionRepository.save(question);
    }

    // ✅ 질문 삭제 (Delete)
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        questionRepository.delete(question);
    }
}
