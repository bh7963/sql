package com.erp.springbasic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/* 
ORM: 
- Object Relational Mapping
- 객체 관계 맵핑
- ORM은 데이터베이스와 클래스 간의 매핑시 값의 불일치와 제약사항을 해결한는 기능을 제공해줌

Entity: RDBMS의 테이블과 Mapping되는 ORM의 클래스
- 웹 애플리케이션 서버와 데이터베이스 서버 간의 데이터 전송및 관리를 위한 객체
*/
// Entity 클래스의 경우 완벽한 캡슐화를 지향
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Entity:
// - 해당 클래스를 Entity클래스로 지정하는 어노테이션
// - JPA에서 데이터관리를 위해 사용되는 주된 객체
// - name속성으로 Entity자체의 이름을 지정할 수 있음
@Entity(name = "sample_table_1")
// @Table:
// - 해당 Entity클래스를 RDBMS의 테이블과 Mapping시키는 어노테이션
// - 만약 java의 클래스명과 RDBMS의 테이블명이 동일하다면 유추하여 Mapping
// - 이름이 서로 다르다면 name속성으로 Mapping할 테이블명을 지정할 수 있음
@Table(name = "sample_table_1")
public class SampleTable1Entity {
    // @Id:
    // - Entity필드 중 PRIMARY KEY로 지정할 필드에 사용하는 어노테이션
    @Id
    /*
    @GeneratedValue:
    - PRIMARY KEY에서 값 자동 생성하는 전략을 지정하는 어노테이션
    - strategy 속성으로 전략을 지정
    - GenerationType.AUTO: JPA가 적절한 생성 전략을 선택
    - GenerationType.IDENTITY: auto_icrement전략 선택
    - GenerationType.SEQUENCE: 데이터베이스의 sequence 생성 전략 선택
    - GenerationType.TABLE: KEY 생성 목적의 테이블을 이용하여 생성하는 전략
     */
    @Column(name = "sample_id", nullable = true, unique = true, updatable = false, length = 10)
    private String sampleId;

    // @Column:
    // - 해당 필드를 테이블의 Column과 Mapping하는 어노테이션
    // - name: Mapping할 Column의 이름
    // - nullable: null포함 여부
    // - unique: unique 제약 여부
    // - length: Column의 길이
    // - 만약 테이블의 Column과 class의 필드명이 같다면 생략가능
    @Column(name = "sample_column", nullable = false)
    private Integer sampleColumn;

}
