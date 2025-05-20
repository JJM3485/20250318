package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 리액트가 돌아가는 주소 (필요시 추가 origin)
                .allowedOrigins("http://localhost:3000")
                // 허용 HTTP 메서드
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 자격증명(쿠키/세션) 허용
                .allowCredentials(true);
    }
}
