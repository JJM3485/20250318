package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(unique = true, nullable = false)
    private String userId;  // 사용자 ID

    @Column(nullable = false)
    private String password;  // 암호화된 비밀번호

    // 기본 생성자
    public Customer() {}

    public Customer(String nickname, String userId, String password) {
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
    }
}
