package ru.gb.market.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfiguration { //класс для конфигурации SpringSecurity версия Spring Security 5.7.0 и выше

    @Bean
    public RestTemplate restTemplate(){//создаем бин для общения между микросервисами
        return new RestTemplate();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //создаем бин SecurityFilter в цепочке фильтров для запроса
        http
                .csrf().disable()//отключаем csrf защиту
                .cors().disable()//отключаем cors
                .authorizeHttpRequests((authz) -> {//конфигурируем правила для EndPoints
                            try {
                                authz
                                        .antMatchers("/api/v1/products/createProduct")
                                        .hasRole("ADMIN")
                                        .antMatchers("/api/v1/products/**").authenticated()
                                        .anyRequest().permitAll()
                                        .and()
                                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//отключаем хранение сессий
                                        .and()
                                        .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));//указываем что бы в случае если пользователь не авторизован HTTP status был 401
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {//подключаем функцию для шифрования
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { // создаем бин AuthenticationManager
        return authenticationConfiguration.getAuthenticationManager();
    }
}
