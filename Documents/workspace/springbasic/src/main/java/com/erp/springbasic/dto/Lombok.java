package com.erp.springbasic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Lombok 라이브러리
// - getter 메서드나 setter 메서드, 생성자, toString 메서드 등 자주 사용되지만 
//    반복적인 작업을 자동으로 생성하는 라이버르러리
// - 프로젝트가 Lombok 라이브러리 의존성을 가지고 있어야함
// - 대부분의 IDE에서  Lombok 어노테이션을 읽 수 없기 때문에 Lombok 추가 확징이 필요


// @Getter: getter메서드 생성
// @Setter: setter 메서드 생성
// @toString: toString 메서드 생성(필드이름과 값을 문자열로 표현)
// @NoArgsConstructor: 빈 생성자 생성
// @AllArgsConstructor: 모든 멤버 변수를 초기화 하는 생성자 생성
// @RequiredArgsConstructor: 필수(final)멤버 변수를 초기화 하는 생성자 생성
// @Data: @Getter, @Setter, @RequiredArgsConstructor등을 하나로 합친 어노테이션
@Getter
@Setter
@AllArgsConstructor
// @NoArgsConstructor // final변수가 있기에 빈생성자를 생성할 수 없음
@RequiredArgsConstructor
public class Lombok {
    final private String field1 ;
    final private String field2 ;
    private String field3;
    // 기본형 boolaen타입일 땐 getter 메서드 이름은 isXXX();
    private boolean field4;
    // 참조형 Boolean타입일 땐 null 값도 받을 수 있기에 getter 메서드를 사용
    private Boolean field5;
    

    
}
