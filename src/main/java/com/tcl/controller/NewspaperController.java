package com.tcl.controller;

import com.tcl.service.NewspaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 广告
 */
@Controller
@RequestMapping("/newspaper")
public class NewspaperController {
	@Autowired
	private NewspaperService newspaperService;

	@RequestMapping("list")
	public String listPage(ModelMap map,HttpServletRequest request,@RequestParam String type){
		map.put("list", newspaperService.selectByType(type));
		return "newspaper/list";
	}

}
