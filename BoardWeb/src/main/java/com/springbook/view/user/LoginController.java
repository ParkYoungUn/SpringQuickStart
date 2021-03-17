package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		System.out.println("LoginController 로그인 화면 이동...");
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}

	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
		System.out.println("LoginController 로그인 인증 처리...");
		UserVO user = userDAO.getUser(vo);
		if (user != null) {
			System.out.println("로그인 성공");
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		} else {
			System.out.println("로그인 실패");
			return "login.jsp";
		}
	}

}
