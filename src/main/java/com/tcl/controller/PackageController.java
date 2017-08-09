package com.tcl.controller;

import com.tcl.model.PackageDetailsModel;
import com.tcl.model.PackageModel;
import com.tcl.model.PackageModelWithBLOBs;
import com.tcl.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/package")
public class PackageController {
	@Autowired
	private PackageService packageService;

	private static final int PAGE_SIZE = 2;

	@RequestMapping("listPage")
	public String listPage(ModelMap map, HttpServletRequest request, String pageNo){
		int page_no;
		boolean isEmpty = false;
		if (pageNo == null || pageNo == "") {
			page_no = 1;
		}
		else {
			page_no = Integer.parseInt(pageNo);
			if (page_no < 1) {
				page_no = 1;
			}
		}
		Map m=new HashMap<String ,Objects>();
		List<PackageModel> package_list = packageService.selectList(m);
		int total_page = (package_list.size() + PAGE_SIZE - 1) / PAGE_SIZE;
		if (total_page < 1) {
			total_page = 1;
			isEmpty = true;
		}
		if(page_no > total_page) {
			page_no = total_page;
		}
		if(!isEmpty) {
			m.put("start_num", (page_no - 1) * PAGE_SIZE);
			m.put("pageSize", PAGE_SIZE);
			List<PackageModel> packages = packageService.selectListByPage(m);
			map.put("list",packages);
		}else {
			map.put("list",new ArrayList<PackageModel>());
		}
		map.put("pageNo", page_no);
		map.put("totalPage", total_page);
		return "package/list";
	}

	@RequestMapping(value = "/query")
	public String queryPackage(ModelMap map, String name, String status, String wjCode, String min_price,
							   String max_price, String min_saleNum, String max_saleNum, String pageNo) {
		int page_no;
		boolean isEmpty = false;
		if (pageNo == null || pageNo == "") {
			page_no = 1;
		}
		else {
			page_no = Integer.parseInt(pageNo);
			if (page_no < 1) {
				page_no = 1;
			}
		}
		Map m=new HashMap<String ,Objects>();
		m.put("name", name.trim());
		m.put("status", status.trim());
		System.out.println("status: " + status);
		m.put("wjCode", wjCode.trim());
		if(min_price != null && min_price != "") {
			m.put("min_price", Long.parseLong(min_price.trim()));
		}
		if(max_price != null && max_price != "") {
			m.put("max_price", Long.parseLong(max_price.trim()));
		}
		if(min_saleNum != null && min_saleNum != "") {
			m.put("min_saleNum", Long.parseLong(min_saleNum.trim()));
		}
		if(max_saleNum != null && max_saleNum != "") {
			m.put("max_saleNum", Long.parseLong(max_saleNum.trim()));
		}
		List<PackageModel> package_list = packageService.queryPackage(m);
		int total_page = (package_list.size() + PAGE_SIZE - 1) / PAGE_SIZE;
		if (total_page < 1) {
			total_page = 1;
			isEmpty = true;
		}
		if(page_no > total_page) {
			page_no = total_page;
		}
		if(!isEmpty) {
			m.put("start_num", (page_no - 1) * PAGE_SIZE);
			m.put("pageSize", PAGE_SIZE);
			List<PackageModel> packages = packageService.queryListByPage(m);
			map.put("list",packages);
		}else {
			map.put("list",new ArrayList<PackageModel>());
		}
		map.put("query_flag", true);
		map.put("pageNo", page_no);
		map.put("totalPage", total_page);
		map.put("status", status.trim());
		map.put("name", name.trim());
		map.put("wjCode", wjCode);
		map.put("min_price", min_price);
		map.put("max_price", max_price);
		map.put("min_saleNum", min_saleNum);
		map.put("max_saleNum", max_saleNum);
		return "package/list";
	}

	/**
	 * 通过ID删除某条记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteById(String id) {
		//对接收到的字符串参数进行基本的处理与判断
		id = id.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(id);
		int result;
		Map<String, String> map = new HashMap<String, String>();
		if(matcher.matches()) {
			result = packageService.deleteById(Long.parseLong(id));
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
	 * 添加套餐
	 * @param packageModel
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addPackage(PackageModelWithBLOBs packageModel) {
		Map<String, String> map = new HashMap<String, String>();
		int result = packageService.addPackage(packageModel);
		if (result > 0) {
			map.put("msg", "success");
		}else {
			map.put("msg", "error");
		}
		return map;
	}

	/**
	 * 通过ID查找套餐
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/acquire", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectById(String id) {
		//对接收到的字符串参数进行基本的处理与判断
		id = id.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (matcher.matches()) {
			map.put("package", packageService.selectById(Long.parseLong(id)));
		} else {
			PackageModelWithBLOBs packageModel = new PackageModelWithBLOBs();
			packageModel.setName("paramIsError");
			map.put("package", packageModel);
		}
		return map;
	}

	/**
	 * 更新套餐
	 * @param packageModel
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateById(PackageModelWithBLOBs packageModel) {
		int result = packageService.updateById(packageModel);
		Map<String, String> map = new HashMap<String, String>();
		if (result > 0) {
			map.put("msg", "success");
		}else {
			map.put("msg", "error");
		}
		return map;
	}
}
