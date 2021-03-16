package com.springbook.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.Controller;

import com.springbook.view.board.GetBoardListController;
import com.springbook.view.board.InsertBoardController;
import com.springbook.view.user.LoginController;

public class HandlerMapping {
	
	private Map<String,Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/insertBoard.do", new InsertBoardController());
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
