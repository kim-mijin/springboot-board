package com.goodee.mvcboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
							@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage);

		//view로 넘길 때는 다시 분리해서 -> view의 가독성과 view담당자의 작업 용이성을 위해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "/board/boardList";
	}
}
