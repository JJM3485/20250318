package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @JsonIgnore
    public List<Answer> getAnswerList() {
        return answerList;
    }

    @PrePersist  // ✅ 질문이 저장되기 전에 자동으로 현재 시간 설정
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
}
