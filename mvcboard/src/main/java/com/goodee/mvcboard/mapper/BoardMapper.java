package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;

@Mapper //Mapper 애노테이션 :  1. mapper xml과 함께하여 dao class가 만들어짐 2. 애노테이션이 있어야 spring에 의하여 BoardMapper를 implements한 class가 만들어짐
public interface BoardMapper {
	
	Board selectBoard(Board board);
	
	int deleteBoard(Board board);
	
	int updateBoard(Board board);
	
	int insertBoard(Board board);
	
	//local_name컬럼과 count()를 반환
	List<Map<String, Object>> selectLocalNameList(); 
	//추상메서드 -> 메서드 구현이 없는 메서드
	//인터페이스는 추상메서드만 가지므로 abstract생략, 인터페이스는 다른 곳에서 구현하도록 하는 것이므로 public 생략 가능
	//@Select를 이용하여 쿼리를 적을 수 있지만 가독성이 떨어진다
	
	//mybatis메서드는 매개값을 하나만 허용
	//param : Map<String, Object> --> int beginRow, int rowPerPage
	List<Board> selectBoardListByPage(Map<String, Object> map); 
	
	//전체 행의 수
	int selectBoardCount(String localName);
}
