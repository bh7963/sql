package com.erp.springbasic.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.erp.springbasic.dto.PostSample1RequestDto;
import com.erp.springbasic.entity.SampleTable1Entity;
import com.erp.springbasic.entity.SampleUserEntity;
import com.erp.springbasic.provider.JwtProvider;
import com.erp.springbasic.repository.SampleTable1Repository;
import com.erp.springbasic.repository.SampleUserRepository;
import com.erp.springbasic.service.SampleService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class SampleImplementService implements SampleService {

    private final SampleUserRepository sampleUserRepository;
    private final SampleTable1Repository sampleTable1Repository;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<String> postSample1(PostSample1RequestDto dto) {

        String sampleId = dto.getSampleId();
        Integer sampleColumn = dto.getSampleColumn();
        
        // SELECT(SQL: SELECT):
        // 1. repository를 이용하여 조회(find All(전체 조회), findById(PRIMARY KEY를 기준으로 조회))
        // SampleTable1Entity existEntity = sampleTable1Repository.findById(sampleId).get();
        // 2. repository를 이용하여 조회(existsById)
        boolean isExisted = sampleTable1Repository.existsById(sampleId);
        if (isExisted) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 기본키 입니다.");
        
        // CREATE(SQL: INSERT)
        // 1. Entity 클래스의 인스턴스 생성
        SampleTable1Entity entity = new SampleTable1Entity(sampleId, sampleColumn);

        // 2. 생성한 인스턴스를 repository를 이용하여 저장
        // save(): 저장
        // - 만약에 PRIMARY KEY가 동일한 레코드가 존재하지 않으면 레코드를 생성
        // - 동일한 레코드가 존재하면 수정
        sampleTable1Repository.save(entity);
        

        return ResponseEntity.status(HttpStatus.CREATED).body("성공");


}

    @Override
    public ResponseEntity<String> deleteSample1(String sampleId) {


       // DELETE (SQL : DELETE)
        // 1. repository를 이용하여 ID(PK)에 해당하는 레코드 삭제
        //    - 해당하는 레코드가 존재하지 않아도 에러가 발생하지 않음
        sampleTable1Repository.deleteById(sampleId);

        // 2. repository를 이용하여 Entity에 해당하는 레코드 삭제
        //    - 해당하는 레코드가 존재하지 않을때 수행 불가능
        SampleTable1Entity entity = sampleTable1Repository.findById(sampleId).get();
        sampleTable1Repository.delete(entity);

        return ResponseEntity.status(HttpStatus.OK).body("성공");
        
    }

    @Override
    public ResponseEntity<String> queryString() {

        List<SampleUserEntity> sampleUserEntities = sampleUserRepository.getNativeSql("홍길동", "부산광역시");
        return ResponseEntity.status(HttpStatus.OK).body(sampleUserEntities.toString());
    }

    @Override
    public String getJwt(String name) {
        String jwt = jwtProvider.create(name);
        return jwt;
    }

    @Override
    public String validateJwt(String jwt) {
        String subject =  jwtProvider.validate(jwt);
        return subject;
    }
}