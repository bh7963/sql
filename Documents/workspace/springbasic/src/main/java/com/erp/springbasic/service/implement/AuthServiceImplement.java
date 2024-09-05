package com.erp.springbasic.service.implement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.erp.springbasic.dto.PostUserRequestDto;
import com.erp.springbasic.dto.SignInRequestDto;
import com.erp.springbasic.entity.SampleUserEntity;
import com.erp.springbasic.provider.JwtProvider;
import com.erp.springbasic.repository.SampleUserRepository;
import com.erp.springbasic.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final JwtProvider jwtProvider;
    private final SampleUserRepository sampleUserRepository;

    // PasswordEncoder 인터페이스:
    // 비밀번호를 안전하고 쉽게 암호화하여 관리할 수 있도록 도움을 주는 인터페이스
    // 구현체 
    // - BCryptPasswordEncoder, SCryptPasswordEncoder, Pbkdf2CryptPasswordEncoder
    // - String encode(평문비밀번호): 평문 비밀번호를 암호화하여 반환
    // - boolean mathes(평문비밀번호, 암호화된비밀번호): 평문 비밀번호와 암호화된 비밀번호가 일치하는지 여부를 반환

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public String signUp(PostUserRequestDto dto) {

        try {
            
            String userId = dto.getUserId();
            boolean isExistedId = sampleUserRepository.existsByUserId(userId);
            if (isExistedId) return "존재하는 아이디";

            String telNumber = dto.getTelNumber();
            boolean isExistedTelNumber = sampleUserRepository.existsByTelNumber(telNumber);
            if (isExistedTelNumber) return "존재하는 전화번호";

            // String password = dto.getPassword();
            // String name =  dto.getName();
            // String address = dto.getAddress();
            // SampleUserEntity userEntity = new SampleUserEntity(userId,password,name,address,telNumber);
            // SampleUserEntity userEntity = new SampleUserEntity();
            // userEntity.setUserId(userId);
            // userEntity.setPassword(password);

            // SampleUserEntity userEntity = 
            //     SampleUserEntity.builder()
            //         .userId(userId)
            //         .password(password)
            //         .name(name)
            //         .address(address)
            //         .telNumber(telNumber)
            //         .build();

            // 비밀번호 암호화
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            SampleUserEntity userEntity = new SampleUserEntity(dto);
            sampleUserRepository.save(userEntity);   


            return "성공!";
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return "예외발생!";
        }
    }


    @Override
    public String signIn(SignInRequestDto dto) {
        try {
            String userId = dto.getUserId();
            SampleUserEntity userEntity = sampleUserRepository.findByUserId(userId);
            if (userEntity == null ) return "로그인정보가 일치하지 않습니다";

            String password = dto.getPassword();
            String encodedpassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedpassword); 
            if (!isMatched) return"로그인정보가 일치하지 않습니다";

            String token = jwtProvider.create(userId);
            return token;
            
        } catch (Exception exception) {
            exception.printStackTrace();;
            return "예외 발생";
        }
    }
    
}
