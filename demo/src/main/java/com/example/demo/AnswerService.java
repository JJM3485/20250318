package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    // ✅ 특정 질문의 모든 답변 가져오기
    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    // ✅ 답변 생성
    @Transactional
    public Answer createAnswer(Long questionId, String content) {
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (questionOpt.isEmpty()) {
            throw new IllegalArgumentException("Question not found: " + questionId);
        }

        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(questionOpt.get());

        return answerRepository.save(answer);
    }

    // ✅ 답변 수정 (UPDATE)
    @Transactional
    public Answer updateAnswer(Long answerId, String newContent) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found: " + answerId));

        answer.setContent(newContent);
        return answerRepository.save(answer);
    }

    // ✅ 답변 삭제 (DELETE)
    @Transactional
    public void deleteAnswer(Long answerId) {
        if (!answerRepository.existsById(answerId)) {
            throw new IllegalArgumentException("Answer not found: " + answerId);
        }
        answerRepository.deleteById(answerId);
    }
}
