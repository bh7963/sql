package com.erp.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.erp.springbasic.dto.Lombok;


@SpringBootApplication
public class SpringbasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbasicApplication.class, args);
	}
	void lombokExample(){
		Lombok lombok = new Lombok("a","b", "c",false, false);
		lombok.getField3();
		lombok.setField3(null);

		// lombok = new Lombok();
		lombok = new Lombok("d", "e");
		
		lombok.isField4();
		lombok.getField5();
	}
}
