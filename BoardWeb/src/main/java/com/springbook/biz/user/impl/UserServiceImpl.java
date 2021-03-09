package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	public UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
//	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

}
