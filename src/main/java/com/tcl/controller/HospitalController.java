package com.tcl.controller;

import com.tcl.model.HospitalModel;
import com.tcl.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@RequestMapping("listHospitals")
	@ResponseBody
	public Map<String, List<HospitalModel>> listHospitals() {
		Map<String, List<HospitalModel>> map = new HashMap<String, List<HospitalModel>>();
		map.put("hospitals", hospitalService.selectByType(""));
		return map;
	}

}
