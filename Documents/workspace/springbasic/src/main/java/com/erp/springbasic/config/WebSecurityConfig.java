package com.erp.springbasic.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.erp.springbasic.filter.JwtAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// Spring Web 보안 설정

//@Confingrable:
// - Spring bean으로 등록되지 않은 클래스에서 @AutoWired를 사용할 수 있도록 하는 어노테이션 
@Configurable
// @Configuration:
// - 생성자나 '메서드'가 호출 시에 Spring Bean으로 등록되게 하는 어노테이션
@Configuration
// @EnableWebSecurity:
// - WebSecurity설정을 지원하는 어노테이션 
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean // 호출이 목적이면 @Bean, 인스턴스 생성이 목적이면 @Component
    protected SecurityFilterChain configure(HttpSecurity security)throws Exception{

        // Class:method
        // - 메서드 참조, 특정 클래스의 메서드를 참조 할 때 사용
        // - 매개변수로 메서드를 전달할 때 자주 사용

        security
            // Basic 인증 방식에 대한 설정
            // Basic 인증 방식을 미사용으로 지정
            .httpBasic(HttpBasicConfigurer::disable)
            // Session: 
            // WebApplication에서 클라이언트의 대한 정보를 유지하기 위한 기술
            // 서버측에서 클라이언트 정보를 유지하는 방법

            // Session 유지 방식에 대한 설정
            // Session 유지를 하지 않겠다고 지정
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // CSRF(Cross-Site Request Forgery)
            // - 클라이언트가 자신의 의도와는 무관하게 공격행위를 하는 것                                                                                                                                                                                     

            // CSRF 취약점에 대한 대비 설정
            // CSRF 취약점에 대한 대비를 하지 않겠다고 지정
            .csrf(CsrfConfigurer::disable)

            // CORS(Cross Origin Resource Sharing)
            // - 서로 다른 출처간의 데이터 공유에 대한 정책
            // - 출처 = 프로토콜, IP주소, 포트

            // CORS정책 설정
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // 요청 URL의 패턴에 따라 인증이 필요한 작업인지 인가가 필요한 작업인지 지정하는 설정
            // - 모든 클라이언트가 접근할 수 있도록 허용
            // - 인증된 모든 클라이언트가 접근할 수 있도록 허용
            // - 인증된 클라이언트 중 특정 권한을 가진 클라이언트만 접근할 수 있도록 허용
            .authorizeHttpRequests(request -> request
                // requestMatchers(): URL 패턴, HTTP메소드 + URL 패턴, 
                // HTTP메소드마다 접근허용 방식을 지정하는 메서드
                // permitAll(): 모든 클라이언트가 접글할 수 있도록 지정
                .requestMatchers("/anyone/**", "/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/samle/jwt/*").permitAll()
                // hasRole(권한): 특정 권한을 가진 클라이언트만 접근 할 수 있도록 지정
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/service").hasRole("ADMIN")
                // authenticated(): 인증된 모든 클라이언트가 접근할 수 있도록 지정
                .requestMatchers("/user/**").authenticated()
                // .requestMatchers(HttpMethod.GET).authenticated()
                .requestMatchers(HttpMethod.POST, "/notice").hasRole("ADMIN")
                // anyRequest(): requestMatchers로 지정한 메서드 혹은 URL이 아닌 모든 요청
                .anyRequest().authenticated()
            )
            // 인증 및 인가 과정에서 발생한 예외를 직접 처리
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new FailedAthenticationEntryPoint())
            )
            // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 이전에 등록
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    };

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
        
    };

}

// 인증 및 인가 실패처리를 위한 커스텀 예외(AuthenticationEntryPoint 인터페이스 구현)
class FailedAthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                
        authException.printStackTrace();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("{\"message\" : \"인증 및 인가에 실패했습니다.\"}");
    }

}

