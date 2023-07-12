package com.goodee.mvcboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	/*
    ANSI_RESET = "\u001B[0m";
    ANSI_BLACK = "\u001B[30m";
    ANSI_RED = "\u001B[31m";
    ANSI_GREEN = "\u001B[32m";
    ANSI_YELLOW = "\u001B[33m";
    ANSI_BLUE = "\u001B[34m";
    ANSI_PURPLE = "\u001B[35m";
    ANSI_CYAN = "\u001B[36m";
    ANSI_WHITE = "\u001B[37m";
    ANSI_BLACK_BACKGROUND = "\u001B[40m";
    ANSI_RED_BACKGROUND = "\u001B[41m";
    ANSI_GREEN_BACKGROUND = "\u001B[42m";
    ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    ANSI_BLUE_BACKGROUND = "\u001B[44m";
    ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    ANSI_CYAN_BACKGROUND = "\u001B[46m";
    ANSI_WHITE_BACKGROUND = "\u001B[47m";
    */
	
	//게시글 상세보기 페이지로 이동
	@GetMapping("/board/boardOne")
	public String boardOne(Model model, Board board) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/board/boardOne";
	}
	
	//게시글 수정폼으로 이동
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, Board board) {
		model.addAttribute("board", boardService.getBoard(board)); //수정 전 데이터를 받아서 model 속성영역에 저장
		return "/board/modifyBoard";
	}
	
	//게시글 수정액션
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) {
		int row = boardService.modifyBoard(board);
		log.debug(""+row);
		return "redirect:/board/boardOne?boardNo="+board.getBoardNo();
	}
	
	//게시글 삭제폼으로 이동
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model, Board board) {
		model.addAttribute("board", boardService.getBoard(board));
		return "/board/removeBoard";
	}
	
	//게시글 삭제액션
	@PostMapping("/board/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		log.debug(""+row);
		return "redirect:/board/boardList";
	}
	
	//addBoard.jsp 입력폼으로 이동
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "/board/addBoard"; //forward
	}
	
	//addBoard 입력액션 처리
	@PostMapping("/board/addBoard")
	public String addBoard(Board board) { //커맨드 객체를 매개값으로 받는다
		int row = boardService.addBoard(board);
		log.debug("\u001B[43m"+"row : " + row + "\u001B[0m");
		return "redirect:/board/boardList"; //redirect
	}
	
	/*
	 springboot
	 메서드의 매개변수를 받는 방법
	 1. @RequestParam
	 2. 커맨드 객체 (폼의 name속성이 dto타입의 필드이름과 일치해야 한다)
	 3. 퍼머링크 사용
	*/
	
	//boardList.jsp
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
							@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
							@RequestParam(name = "localName", required = false) String localName) {
		//required옵션은 디폴트가 true
		log.debug(""+localName); //안넘어오면 null 
		
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage, localName);

		//view로 넘길 때는 다시 분리해서 -> view의 가독성과 view담당자의 작업 용이성을 위해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		model.addAttribute("localName", localName);
		
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("pageBlock", resultMap.get("pageBlock"));
		model.addAttribute("beginPage", resultMap.get("beginPage"));
		model.addAttribute("endPage", resultMap.get("endPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "/board/boardList";
	}
}
