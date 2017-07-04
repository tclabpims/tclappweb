package com.tcl.controller;

import com.tcl.model.WebUser;
import com.tcl.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/webUser")
public class WebUserController {
	@Autowired
	private WebUserService webUserService;

	@RequestMapping("login")
	public String login(ModelMap map,HttpServletRequest request,@RequestParam String userName,@RequestParam String password){
		WebUser user=webUserService.selectByUserName(userName);
		if(user!=null&&user.getPassword().equals(password)){
			request.getSession().setAttribute("userName",userName);
			request.getSession().setAttribute("userId",user.getId());
			return "main";
		}else {
			map.put("code","-1");
			map.put("message","用户不存在或者密码错误！");
			return "login";
		}

	}

	@RequestMapping("index")
	@ResponseBody
	public WebUser index(){
		WebUser user=webUserService.selectById(1L);
		return user;
	}
	
}
