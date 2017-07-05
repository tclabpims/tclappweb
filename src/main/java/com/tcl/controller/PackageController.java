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
@RequestMapping("/package")
public class PackageController {
	@Autowired
	private WebUserService webUserService;

	@RequestMapping("listPage")
	public String listPage(ModelMap map,HttpServletRequest request,@RequestParam String userName,@RequestParam String password){

		return "login";
	}

	@RequestMapping("index")
	@ResponseBody
	public WebUser index(){
		WebUser user=webUserService.selectById(1L);
		return user;
	}
	
}
