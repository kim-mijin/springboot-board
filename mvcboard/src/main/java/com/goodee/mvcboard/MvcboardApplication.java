package com.goodee.mvcboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //spring에서 구동되지 않는 Sevlet애노테이션(WebFilter, WebListenter)도 찾는 애노테이션
public class MvcboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcboardApplication.class, args);
	}

}
