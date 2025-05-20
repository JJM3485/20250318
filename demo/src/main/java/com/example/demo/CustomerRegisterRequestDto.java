package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegisterRequestDto {
    private String nickname;
    private String userId;
    private String password;
}
