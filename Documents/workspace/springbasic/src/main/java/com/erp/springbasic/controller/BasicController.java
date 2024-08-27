package com.erp.springbasic.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Controller 레이어:
// - 클라이언트와 서버간의 접점
// - 클라이언트의 요청을 받고 해당 요청에 대한 응답을 처리함
// - 각 요청에 헤딩하는 URL메서드를 작성하는 영역   

// @Controller: 해당 클래스가  Controller Layer임을 명시, 단 응답 데이터가 HTML형식임
// @ResponseBody: 응답의 데이터 타입을 HTML이 아니라 JSON으로 지정하는 어노테이션 

// @RestController: @Controller + @ResponseBody, JSON형식의 데이터를 반환하는 컨트롤러임을 명시
@RestController

// @REquestMapping: HTTP요청에 클래스와 메서드를 URL로 맵핑하기 위한 어노테이션
// HTTP GET localhost:4000/main/** 
// @RequestMapping(value="/main", method = RequestMethod.GET )
@RequestMapping("/basic")
public class BasicController {

    @RequestMapping(value="/first", method = RequestMethod.GET)
    public String firstMethod(){
        return "첫번째 REST API 요청 응답입니다.";
    }
    
    // HTTP GET method: 클라이언트가 서버로부터 데이터를 받기 위한 메서드(RequestBody X)
    @GetMapping("")
    public String getMethod(){
        return "GET Method";
    }
    // HTTP POST method: 클라이언트가 서버에 리소스를 작성하기 위한 메서드(RequestBody O)
    @PostMapping("")
    public String postMethod(){
        return "Post Method";
    }
    // HTTP PUT method: 클라이언트가 서버에 리소스를 전체 수정하기 위한 메서드(RequestBody O)
    @PutMapping("")
    public String putMethod(){
        return "Put Method";
    }
    // HTTP PATCH method: 클라이언트가 서버에 리소스를 일부 수정하기 위한 메서드(RequestBody O)
    @PatchMapping("")
    public String patchMethod(){
        return "Patch Method";
    }
    // HTTP DELETE method: 클라이언트가 서버에 리소스를 삭제하기 위한 메서드(RequestBody X)
    @DeleteMapping()
    public String deleteMethod(){
        return "Delete Method";
    }
    // Method + URL Pattern이 중복되면 서버 실행중에 에러가 발생(반드시 하나라도 달라야함)
    // @GetMapping("/first")
    // public String getFirst(){
    //     return  "Get First";                 
    // }

}