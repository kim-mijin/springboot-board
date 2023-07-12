package com.goodee.mvcboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.vo.Board;

@Service //spring은 @Component(Controller, Service, Mapper의 부모)가 붙은 클래스를 찾아 객체를 만들어 놓는다
@Transactional
public class BoardService {
	@Autowired 
	private BoardMapper boardMapper;
	
	public Board getBoard(Board board) {
		return boardMapper.selectBoard(board);
	}
	
	public int removeBoard(Board board) {
		return boardMapper.deleteBoard(board);
	}

	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName){
		
		//service layer 역할1 : controller가 넘겨준 매개값을 dao의 매개값에 맞게 가공
		int beginRow = (currentPage-1)*rowPerPage;
		//반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		//반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		//service layer 역할2 : dao에서 반환받은 값을 가공하여 controller에 반환
		//페이지네이션
		int boardCount = boardMapper.selectBoardCount(localName);
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage += 1;
		}
		int pageBlock = 10;
		int beginPage = ((currentPage-1)/pageBlock)*pageBlock + 1;
		int endPage = beginPage + pageBlock;
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);
		resultMap.put("pageBlock", pageBlock);
		resultMap.put("beginPage", beginPage);
		resultMap.put("endPage", endPage);
		
		return resultMap; 
	}
}
