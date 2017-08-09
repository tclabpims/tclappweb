package com.tcl.controller;

import com.tcl.model.WebUser;
import com.tcl.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@RequestMapping(value = "/acquireUserName", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getUserName(String createUserid) {
		createUserid = createUserid.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(createUserid);
		Map<String, String> map = new HashMap<String, String>();
		if (matcher.matches()) {
			WebUser webUser = webUserService.selectById(Long.parseLong(createUserid));
			if (webUser != null) {
				map.put("username", webUser.getUserName());
			} else {
				map.put("username", "error");
			}
		} else {
			map.put("username", "error");
		}
		return map;
	}
}
