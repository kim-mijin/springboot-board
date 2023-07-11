package com.goodee.mvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //... implements Servlet
public class HomeController {
	@GetMapping("/home") //web.xml의 url 패턴매핑 또는 @WebServlet()을 대신한다
	public String home() {
		return "home"; //RequestDispatcher.forward()
	}
}
 