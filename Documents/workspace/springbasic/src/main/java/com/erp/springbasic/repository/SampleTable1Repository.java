package com.erp.springbasic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.springbasic.entity.SampleTable1Entity;

/*
Repository Layer:
- 데이터베이스 작업을 수행하는 영역

@Repository: 해당 클래스를 Spring Bean으로 등록하는 어노테이션, @Component와 동일
- interface에 @Repository를 적용한 이유
- JPA를 사용하면 JPARepository 인터페이스를 상속받은 interface는 JPA가 구현체를 자동으로 생성
*/
@Repository
public interface SampleTable1Repository 
// JpaRepository<T, ID> 인터페이스
// - JPA기반의 Repository를 구현하는 주요 인터페이스
// - 기본 CRUD기능 및 Pagging 기능들을 포함
// - JPA기반의 Repository를 생성할 땐 필수로 상속해야함
// - 매개타입 T: 해당 Repository가 어떤 Entity의 Repository인지 지정
// - 매개타입 ID: 해당 Repository가 사용하는 Entity의 기본키 타입을 지정 
extends JpaRepository<SampleTable1Entity, String> {
    
}
