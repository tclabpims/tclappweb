package com.tcl.service.impl;

import com.tcl.dao.WebUserMapper;
import com.tcl.model.WebUser;
import com.tcl.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WebUserServiceImpl implements WebUserService {

	@Autowired
	private WebUserMapper userDao;

	public WebUser selectById(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

	public WebUser selectByUserName(String userName) {
		return userDao.selectByUserName(userName);
	}
}
