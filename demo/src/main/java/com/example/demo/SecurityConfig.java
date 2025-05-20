package com.example.demo;

import com.example.demo.CustomerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomerDetailsService customerDetailsService;

    public SecurityConfig(CustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(customerDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> {})
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions().disable())  // H2 콘솔 용
        .authorizeHttpRequests(auth -> auth
            //.requestMatchers("/h2-console/**", "/auth/register").permitAll()
            .requestMatchers("/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginProcessingUrl("/login")            // POST /login 처리
            .usernameParameter("userId")             // 폼 파라미터 이름
            .passwordParameter("password")
            .successHandler((req, res, auth) -> res.setStatus(200))  // 200 OK 반환
            .failureHandler((req, res, ex) -> res.sendError(401, "로그인 실패"))
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/auth/logout")
            .logoutSuccessHandler((req, res, auth) -> res.setStatus(200))
            .permitAll()
        )
        .httpBasic(httpBasic -> httpBasic.disable());

    return http.build();
}




    public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
        public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
            super.setAuthenticationManager(authenticationManager);
            super.setUsernameParameter("userId");
            super.setPasswordParameter("password");
        }
    }
}
