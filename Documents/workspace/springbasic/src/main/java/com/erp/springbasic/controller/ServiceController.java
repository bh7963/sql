package com.erp.springbasic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.springbasic.service.BasicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/service")

@RequiredArgsConstructor
public class ServiceController {

    // 의존성 주입(DI):
    // - 해당 모듈에 필요한 의존성을 외부(클래스의 인스턴스를 생성하는 위치)에서 주입
    // - 생성자를 통한 의존성 주입, setter메서드를 통한 의존성 주입, 필드를 통한 의존성 주입
    // - SpringFramework에서 권장하고 있는 의존성 주입 방법은 
    //  생성자를 통한 의존성 주입(의존성이 주입되지 않은 상태가 존재할 수 없기 때문)
    // - Spring Framework에서 IoC을 통해 의존성을 주입할 땐 주입할 인스턴스의 클래스가 Spring Bean으로 등록 되어있어야 함 
    // private BasicService basicService;

    // @Autowired: 등록된 Spring Bean을 제어 역전을 통해 의존성 주입을 할 수 있도록 하는 어노테이션
    
    // 필드 객체
    // @Autowired
    // private BasicService basicService;

    // setter 메서드
    // private BasicService basicService;
    
    // @Autowired
    // public void setBasicService(BasicService basicService){
    //     this.basicService = basicService;
    // }

    // 생성자
    private final BasicService basicService; 

    // 생성자를 통한 의존성 주입방식에는 @Autowired를 걸어줄 필요가 없음(생성자를 통한 방식은 SpringFramework에서 권장하는 방식이기 때문)
    // @Autowired
    // public ServiceController(BasicService basicService){
    //     this.basicService = basicService;
    // }

    // private Date today;


    @GetMapping("")
    public ResponseEntity<String> getService(){
        return basicService.getService();
    }
    
    
}
