package com.erp.springbasic.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.erp.springbasic.service.BasicService;

// Service Layer
// - 실제 비즈니스 로직을 실행하는 영역
// - 트랜잭션(기능단위)처리나 유효성 검사(이전 트랜잭션결과의 유효성 검사)를 수행함
// - Controller로 부터 받은 요청을 처리하고 해당 처리중 데이터베이스 작업이 필요하면 Repository영역으로 요청

// @Service : 해당클래스를 Spring Bean으로 등록하는 어노테이션, @Component와 동일하지만 가독성 향상을 위해 Service이름을 사용
// @Component
@Service
public class BasicServiceImplement implements BasicService {

    @Override
    public ResponseEntity<String> getService() {
        return ResponseEntity.status(HttpStatus.OK).body("서비스호출");
    }
    
}
