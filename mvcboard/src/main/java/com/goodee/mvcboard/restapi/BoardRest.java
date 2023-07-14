package com.goodee.mvcboard.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.mvcboard.service.BoardService;

@CrossOrigin //cross brower의 제한을 풀어주는 open rest api를 만들기 위한 애노테이션
@RestController
public class BoardRest {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/rest/localNameList")
	public List<Map<String, Object>> getLocalNameList(){
		return boardService.getLocalNameList(); //@RestController가 달려있어 spring이 JSON으로 변환하여 리턴
	}
}
