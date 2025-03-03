package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF korumasını devre dışı bırakıyoruz (isteğe bağlı)
                .authorizeRequests()
                .anyRequest().permitAll() // Diğer tüm istekler de açık
                .and()
                .formLogin().disable() // Form tabanlı giriş sistemini devre dışı bırakıyoruz
                .httpBasic().disable(); // Basic Auth'ı devre dışı bırakıyoruz (isteğe bağlı)
        return http.build();
    }
}
