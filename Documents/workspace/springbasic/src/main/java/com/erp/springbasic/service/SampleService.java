package com.erp.springbasic.service;

import org.springframework.http.ResponseEntity;

import com.erp.springbasic.dto.PostSample1RequestDto;

public interface SampleService {

    ResponseEntity<String> postSample1(PostSample1RequestDto Dto);
    
}
