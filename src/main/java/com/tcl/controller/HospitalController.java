package com.tcl.controller;

import com.tcl.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 医院
 */
@Controller
@RequestMapping("/hospital")
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;

	@RequestMapping("list")
	public String listPage(ModelMap map,HttpServletRequest request,@RequestParam String type){
		map.put("list", hospitalService.selectByType(type));
		return "hospital/list";
	}

}
