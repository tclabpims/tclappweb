package com.tcl.controller;

import com.tcl.model.DoctorModel;
import com.tcl.model.HospitalModel;
import com.tcl.model.HospitalModelWithBLOBs;
import com.tcl.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 医院
 */
@Controller
@RequestMapping("/hospital")
public class HospitalController {

	private static final int PAGE_SIZE = 5;

	@Autowired
	private HospitalService hospitalService;

	@RequestMapping("list")
	public String listPage(ModelMap map,HttpServletRequest request,@RequestParam String type,
							@RequestParam(required = false) String pageNo){
		int page_no;
		boolean isEmpty = false;
		if (pageNo == null || pageNo == "") {
			page_no = 1;
		}else {
			page_no = Integer.parseInt(pageNo.trim());
			if (page_no < 1) {
				page_no = 1;
			}
		}
		List<HospitalModel> hospitals_all = hospitalService.selectByType(type.trim());
		int totalPage = (hospitals_all.size() + PAGE_SIZE - 1) / PAGE_SIZE;
		if(totalPage < 1) {
			totalPage = 1;
			isEmpty = true;
		}
		if(page_no > totalPage) {
			page_no = totalPage;
		}
		if (!isEmpty) {
			List<HospitalModel> hospitals = hospitalService.selectByPageInfo(type, page_no, PAGE_SIZE);
			map.put("list", hospitals);
		} else {
			map.put("list", new ArrayList<DoctorModel>());
		}
		map.put("pageNo", page_no);
		map.put("totalPage", totalPage);
		map.put("type", type);
		return "hospital/list";
	}

	/**
	 * 获取医院名称
	 * @return
	 */
	@RequestMapping("listHospitals")
	@ResponseBody
	public Map<String, List<HospitalModel>> listHospitals() {
		Map<String, List<HospitalModel>> map = new HashMap<String, List<HospitalModel>>();
		map.put("hospitals", hospitalService.selectByType(""));
		return map;
	}

	/**
	 * 增加医院
	 * @param hospitalModel
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, String> addHospital(HospitalModelWithBLOBs hospitalModel) {
		int result = hospitalService.addHospital(hospitalModel);
		Map<String, String> map = new HashMap<String, String>();
		if(result > 0) {
			map.put("msg", "success");
		} else {
			map.put("msg", "error");
		}
		return map;
	}

	/**
	 * 根据条件查询医院
	 * @param map
	 * @param name
	 * @param telphone
	 * @return
	 */
	@RequestMapping(value = "/query")
	public String queryHospitalByInfo(ModelMap map, String name, String telphone, String pageNo) {
		int page_no;
		boolean isEmpty = false;
		if(pageNo == null || pageNo == "") {
			page_no = 1;
		} else {
			page_no = Integer.parseInt(pageNo);
			if (page_no < 1) {
				page_no = 1;
			}
		}
		List<HospitalModel> hospitals_all = hospitalService.queryByInfo(name.trim(), telphone.trim());
		int totalPage = (hospitals_all.size() + PAGE_SIZE - 1) / PAGE_SIZE;
		if (totalPage < 1) {
			totalPage = 1;
			isEmpty = true;
		}
		if(page_no > totalPage) {
			page_no = totalPage;
		}
		if(!isEmpty) {
			List<HospitalModel> hospitals = hospitalService.queryByPageInfo(name.trim(), telphone.trim(), page_no, PAGE_SIZE);
			map.put("list", hospitals);
		} else {
			map.put("list", new ArrayList<HospitalModelWithBLOBs>());
		}
		map.put("name", name);
		map.put("telphone", telphone);
		map.put("pageNo", page_no);
		map.put("totalPage", totalPage);
		map.put("query_flag", true);
		return "hospital/list";
	}

	/**
	 * 通过ID将某位医生删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteHospitalById(String id) {
		//对接收到的字符串参数进行基本的处理与判断
		id = id.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(id);
		int result;
		Map<String, String> map = new HashMap<String, String>();
		if(matcher.matches()) {
			result = hospitalService.deleteById(Long.parseLong(id));
			if(result > 0) {
				//返回1表示删除成功
				map.put("msg", "1");
			} else {
				//返回2表示删除失败
				map.put("msg", "2");
			}
		} else {
			//返回3表示参数有误
			map.put("msg", "3");
		}
		return map;
	}

	/**
	 * 通过ID查找某家医院
	 * @param id
	 * @return
	 */
	@RequestMapping("/acquire")
	@ResponseBody
	public Map<String, Object> selectById(String id) {
		//对接收到的字符串参数进行基本的处理与判断
		id = id.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (matcher.matches()) {
			map.put("hospital", hospitalService.selectById(Long.parseLong(id)));
		} else {
			HospitalModelWithBLOBs hospitalModel = new HospitalModelWithBLOBs();
			hospitalModel.setName("paramIsError");
			map.put("hospital", hospitalModel);
		}
		return map;
	}

	/**
	 * 对某家医院的信息进行更新
	 * @param hospitalModel
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateById(HospitalModelWithBLOBs hospitalModel) {
		int result = hospitalService.updateById(hospitalModel);
		Map<String, String> map = new HashMap<String, String>();
		if (result > 0) {
			map.put("msg", "success");
		}else {
			map.put("msg", "error");
		}
		return map;
	}
}
