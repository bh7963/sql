package com.erp.springbasic.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.erp.springbasic.entity.SampleUserEntity;
import com.erp.springbasic.provider.JwtProvider;
import com.erp.springbasic.repository.SampleUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// - filter:
// - 서버 로직과 서블릿 사이에서 http request에 대한 사전 검사 작업을 수행하는 영역
// - filter에서 걸러진 request는 서블릿 까지 도달하지 못하고 reject됨

// OncePerRequestFilter라는 추상 클래스를 확장 구현하여 filter클래스로 생성
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final SampleUserRepository sampleUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    // 1. Request 객체에서 Token가져오기
                    String token = parseBearerToken(request);
                    if (token == null) {
                        filterChain.doFilter(request, response);
                        return;
                    }

                    // 2. Token 검증
                    String subject = jwtProvider.validate(token);
                    if (subject == null) {
                        filterChain.doFilter(request, response);
                        return;
                    }

                    // 3. 데이터베이스에 존재하는 유저인지 확인
                    SampleUserEntity userEntity =  sampleUserRepository.findByUserId(subject);
                    if (userEntity == null) {
                        filterChain.doFilter(request, response);
                        return;
                    }

                    // 4. 접근주체의 권한 리스트 지정
                    List<GrantedAuthority> roles = AuthorityUtils.NO_AUTHORITIES;
                    if(subject.equals("qwer1234")){
                        roles = new ArrayList<>();
                        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    }

                    // 5. printiple에 대한 정보를 controller로 전달하기 위해 context에 저장
                    
                    // 5.1 인증된 사용자 객체를 생성
                    // UsernamePasswordAuthenticationToken(사용자 정보, 비밀번호, 권한)
                    AbstractAuthenticationToken authenticationToken 
                        = new UsernamePasswordAuthenticationToken(subject, null, roles);
                        // = new UsernamePasswordAuthenticationToken(subject, null, roles);

                        
                    // 5.2 인증정보에 상세 request를 등록
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // 5.3 빈 securiy context생성
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                    // 5.4 security context 생성한 인증정보 토큰을 등록
                    securityContext.setAuthentication(authenticationToken);

                    // 5.5 생성한 security context를 등록
                    SecurityContextHolder.setContext(securityContext);
                    
                } catch (Exception exception) {
                    
                    exception.printStackTrace();
                    
                }
                // 6. 다음 filter에 request와 response를 전달
                filterChain.doFilter(request, response);

                
    }
    // 1. request 객체 header 중 'Authorization' 필드의 값을 가져옴
    // 2. 'Authorization' 필드 값이 Bearer 형식인지 확인
    // 3. 'Authorization' 필드 값에서 token을 추출
    private String parseBearerToken(HttpServletRequest request){

        // 1. request 객체 header 중 'Authorization' 필드의 값을 가져옴
        String authorizaiton = request.getHeader("Authorization");

        // 2. 'Authorization' 필드 값이 Bearer 형식인지 확인
        // 'Authorization' 필드 값의 존재 여부 확인
        boolean hasAuthorization = StringUtils.hasText(authorizaiton);
        if(!hasAuthorization) return null;

        // - 문자열이 "Bearer "로 시작하는지 여부 확인
        boolean isBearer = authorizaiton.startsWith("Bearer ");
        if (!isBearer) return null;

        // 3. 'Authorization' 필드 값에서 token을 추출
        String token =  authorizaiton.substring(7);
        return token;
    }


}
