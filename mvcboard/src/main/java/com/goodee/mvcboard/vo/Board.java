package com.goodee.mvcboard.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String localName;
	private String boardTitle;
	private String boardContent;
	private String memberId;
	private String createdate;
	private String updatedate;
	
	//tabel속성은 아니고 입력폼속성 -> BoardForm.class(DTO)와 Board.class(Domain)을 나누는 것이 좋다
	private List<MultipartFile> multipartFile;
}
