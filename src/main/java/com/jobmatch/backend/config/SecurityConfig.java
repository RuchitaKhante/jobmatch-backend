package com.jobmatch.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(
            	        "/",
            	        "/*.html",
            	        "/css/**",
            	        "/js/**",
            	        "/images/**",

            	        "/user/register",
            	        "/user/login",
            	        "/ml/**",
            	        "/jobs/**",
            	        "/resume/**",
            	        "/apply/**"
            	    ).permitAll()
            	    .anyRequest().authenticated()
            	)

            // ENABLE LOGIN SYSTEM
            .formLogin(form -> form.permitAll())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}