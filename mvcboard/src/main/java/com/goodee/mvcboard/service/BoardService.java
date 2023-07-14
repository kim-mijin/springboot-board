package com.goodee.mvcboard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Service //spring은 @Component(Controller, Service, Mapper의 부모)가 붙은 클래스를 찾아 객체를 만들어 놓는다
@Transactional
public class BoardService {
	@Autowired 
	private BoardMapper boardMapper;
	@Autowired
	private BoardfileMapper boardfileMapper;
	
	//REST API chart에서 호출
	public List<Map<String, Object>> getLocalNameList(){
		return boardMapper.selectLocalNameList();
	}
	
	public Map<String, Object> getBoard(Board board) {
		Board resultBoard = boardMapper.selectBoard(board);
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileList(board);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("board", resultBoard);
		resultMap.put("boardfileList", boardfileList);
		return resultMap;
	}
	
	public int removeBoard(Board board, String path) {
		//저장된 파일 삭제
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileList(board);
		if(boardfileList != null && boardfileList.size() > 0) {
			for(Boardfile bf : boardfileList) {
				File f = new File(path+bf.getSaveFilename());
				if(f.exists()) {
					f.delete();
				}
			}
			//boardfile테이블에서 파일삭제
			int boardfileRow = boardfileMapper.deleteBoardfile(board); //자식테이블인 boardfile을 먼저 삭제해야한다
		}
		//게시글 삭제
		int boardRow = boardMapper.deleteBoard(board);
		return boardRow;
	}
	
	public int modifyBoard(Board board, String path) {
		//게시글 수정
		int boardRow = boardMapper.updateBoard(board);
		
		//첨부파일 수정
		//기존파일 삭제
		boardfileMapper.deleteBoardfile(board);
		//새로운 파일 입력
		this.addBoardfile(board, path);

		return boardRow;
	}
	
	public int addBoardfile(Board board, String path) {
		int row = 0;
		
		//첨부된 파일이 1개 이상이 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		if(fileList != null && fileList.size() > 0) {
			int boardNo = board.getBoardNo(); //board를 입력하기 전에 boardNo호출하면 null이 아닌 기본타입이므로 0이 호출 된다. 따라서 호출 순서에 유의!
			
			for(MultipartFile mf : fileList) { //첨부된 파일의 수만큼 반복\
				if(mf.getSize() > 0) { //파일의 사이즈가 0보다 큰 경우에만 저장, 해당 조건이 없으면 에러가 난다
					Boardfile bf = new Boardfile();
					bf.setBoardNo(boardNo); //부모키값
					bf.setOriginFilename(mf.getOriginalFilename()); //파일원본이름 -> db에 확장자 속성을 만들어 따로 저장하는 것이 좋다
					bf.setFilesize(mf.getSize()); //파일 사이즈
					bf.setFiletype(mf.getContentType()); //파일 타입(MIME)
					//저장될 파일이름
					//확장자
					String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
					//새로운 이름(UUID) + 확장자(ext)
					bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
					
					//테이블에 저장
					row = boardfileMapper.insertBoardfile(bf);
					
					//파일 저장(저장위치필요 : 1.정적으로 작성 2.동적으로 controller에서 받아오기->path변수)
					File f = new File(path+bf.getSaveFilename()); //path위치에 새로운 이름으로 새로운 파일 생성
					//빈파일에 첨부된 파일의 스트림을 주입한다
					try {
						mf.transferTo(f);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
	
						//트랜잭션 작동을 위해 예외를 발생시켜야 한다 -> try... catch...를 강제하지 않는 예외(RuntimeException)로 바꾼다
						throw new RuntimeException();
					}
				}
			}
		}
		
		return row;
	}
	
	public int addBoard(Board board, String path) {
		int row = boardMapper.insertBoard(board);
		
		//입력에 성공하고, 첨부된 파일이 1개 이상이 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		if(row == 1 && fileList != null && fileList.size() > 0) {
			int boardNo = board.getBoardNo(); //board를 입력하기 전에 boardNo호출하면 null이 아닌 기본타입이므로 0이 호출 된다. 따라서 호출 순서에 유의!
			
			for(MultipartFile mf : fileList) { //첨부된 파일의 수만큼 반복\
				if(mf.getSize() > 0) { //파일의 사이즈가 0보다 큰 경우에만 저장, 해당 조건이 없으면 에러가 난다
					Boardfile bf = new Boardfile();
					bf.setBoardNo(boardNo); //부모키값
					bf.setOriginFilename(mf.getOriginalFilename()); //파일원본이름 -> db에 확장자 속성을 만들어 따로 저장하는 것이 좋다
					bf.setFilesize(mf.getSize()); //파일 사이즈
					bf.setFiletype(mf.getContentType()); //파일 타입(MIME)
					//저장될 파일이름
					//확장자
					String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
					//새로운 이름(UUID) + 확장자(ext)
					bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
					
					//테이블에 저장
					boardfileMapper.insertBoardfile(bf);
					
					//파일 저장(저장위치필요 : 1.정적으로 작성 2.동적으로 controller에서 받아오기->path변수)
					File f = new File(path+bf.getSaveFilename()); //path위치에 새로운 이름으로 새로운 파일 생성
					//빈파일에 첨부된 파일의 스트림을 주입한다
					try {
						mf.transferTo(f);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
	
						//트랜잭션 작동을 위해 예외를 발생시켜야 한다 -> try... catch...를 강제하지 않는 예외(RuntimeException)로 바꾼다
						throw new RuntimeException();
					}
				}
			}
		}
		
		return row;
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
