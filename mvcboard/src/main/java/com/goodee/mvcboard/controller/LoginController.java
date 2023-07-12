package com.goodee.mvcboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@SessionAttributes(names = "로그인정보키") //로그인정보키라는 키를 가진 model의 생명주기를 session으로
public class LoginController {
	//로그인 폼
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	//로그인 액션
	@PostMapping("/login")
	public String login(HttpSession session,
						@RequestParam(name="memberId") String memberId,
						@RequestParam(name="memberPw") String memberPw) {
		//service(memberId, memberPw) -> mapper -> 로그인 성공 유무 반환
		
		//로그인 성공 시
		session.setAttribute("","");
		
		return "redirect:/login";
	}
	
	//로그인 액션
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
}
