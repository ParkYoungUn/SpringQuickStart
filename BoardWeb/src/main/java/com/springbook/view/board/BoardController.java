package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	// 글 등록
	@RequestMapping(value="insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("BoardController 글 등록 처리");
		boardDAO.insertBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
		System.out.println("BoardController 글 수정 처리");
		System.out.println("번호: "+vo.getSeq());
		System.out.println("제목: "+vo.getTitle());
		System.out.println("작성자: "+vo.getWriter());
		System.out.println("내용: "+vo.getContent());
		System.out.println("등록일: "+vo.getRegDate());
		System.out.println("조회수: "+vo.getCnt());
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("BoardController 글 삭제 처리");
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
		
	}
	
	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println("BoardController 글 상세 조회 처리");
//		mav.addObject("board",boardDAO.getBoard(vo)); // Model 정보 저장
//		mav.setViewName("getBoard.jsp"); // View 정보 저장
		model.addAttribute("board", boardDAO.getBoard(vo));
		
		return "getBoard.jsp";
	}
	
	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
//	public ModelAndView getBoardList(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
	public String getBoardList(@RequestParam(value="searchCondition", defaultValue="TITLE",required=false) String condition,
			@RequestParam(value="searchKeyword", defaultValue="",required=false) String keyword,
			BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println("BoardController 글 목록 검색 처리");
		System.out.println("검색 조건: "+condition);
		System.out.println("검색 단어: "+keyword);
//		mav.addObject("boardList",boardDAO.getBoardList(vo)); // Model 정보 저장
//		mav.setViewName("getBoardList.jsp"); // View 정보 저장
		
		model.addAttribute("boardList", boardDAO.getBoardList(vo));
		
		return "getBoardList.jsp";
	}
}
