package com.tcl.controller;

import com.tcl.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/package")
public class PackageController {
	@Autowired
	private PackageService packageService;

	@RequestMapping("listPage")
	public String listPage(ModelMap map,HttpServletRequest request){
		Map m=new HashMap<String ,Objects>();
		map.put("list",packageService.selectList(m));
		return "package/list";
	}

}
