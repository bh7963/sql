package com.erp.springbasic.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSample1RequestDto {
    @NotBlank
    @Length(max=10)
    private String sampleId;
    
    @NotNull
    private Integer sampleColumn;
    
    
    
}
