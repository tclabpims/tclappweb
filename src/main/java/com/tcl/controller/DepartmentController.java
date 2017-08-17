package com.tcl.controller;

import com.tcl.model.DepartmentModel;
import com.tcl.model.PackageDetailsModel;
import com.tcl.service.DepartmentService;
import com.tcl.service.PackageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping("/acquireAllDeparment")
    @ResponseBody
    public Map<String, Object> getAllDepartment() {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        List<DepartmentModel> all_departments = departmentService.selectList(mapInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("num: " + all_departments.size());
        map.put("all_departments", all_departments);
        return map;
    }

    /**
     * 列出所有检验细项
     * @param map
     * @param pageNo
     * @return
     */
    @RequestMapping("/list")
    public String departmentList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Map<String, Object> result_map = getData(mapInfo);
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        map.put("list", result_map.get("list"));
        return "packageDetails/list";
    }

    /**
     * 通过参数查询检验细项
     * @param map
     * @param hisName
     * @param packageName
     * @param name
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String queryPackageDetails(ModelMap map, String hisName, String packageName, String name, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("hisName", hisName.trim());
        mapInfo.put("packageName", packageName.trim());
        mapInfo.put("name", name.trim());
        Map<String, Object> result_map =  getData(mapInfo);
        map.put("hisName", hisName.trim());
        map.put("packageName", packageName.trim());
        map.put("name", name.trim());
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        map.put("list", result_map.get("list"));
        map.put("query_flag", true);
        return "packageDetails/list";
    }

    /**
     * 增加检验细项
     * @param departmentModel
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addPackageDetails(DepartmentModel departmentModel) {
        Map<String, String> map = new HashMap<String, String>();
        int result = departmentService.addPackageDetails(departmentModel);
        if (result > 0) {
            map.put("msg", "success");
        }else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 通过ID查找检验细项
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
            map.put("packageDetails", departmentService.selectById(Long.parseLong(id)));
        } else {
            PackageDetailsModel packageDetailsModel = new PackageDetailsModel();
            packageDetailsModel.setName("paramIsError");
            map.put("packageDetails", packageDetailsModel);
        }
        return map;
    }

    /**
     * 更新套餐
     * @param DepartmentModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateById(DepartmentModel DepartmentModel) {
        int result = departmentService.updateById(DepartmentModel);
        Map<String, String> map = new HashMap<String, String>();
        if (result > 0) {
            map.put("msg", "success");
        }else {
            map.put("msg", "error");
        }
        return map;
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
            result = departmentService.deleteById(Long.parseLong(id));
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

    private Map<String, Object> getData(Map<String, Object> mapInfo) {
        int page_no;
        boolean isEmpty = false;
        Map<String, Object> map = new HashMap<String, Object>();
        if (mapInfo.get("pageNo") == null || mapInfo.get("pageNo") == "") {
            page_no = 1;
        }
        else {
            page_no = Integer.parseInt(((String)mapInfo.get("pageNo")).trim());
            if (page_no < 1) {
                page_no = 1;
            }
        }
        List<DepartmentModel> department_list = departmentService.selectList(mapInfo);
        int total_page = (department_list.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        if (total_page < 1) {
            total_page = 1;
            isEmpty = true;
        }
        if(page_no > total_page) {
            page_no = total_page;
        }
        map.put("pageNo", page_no);
        map.put("totalPage", total_page);
        if(!isEmpty) {
            mapInfo.put("start_num", (page_no - 1) * PAGE_SIZE);
            mapInfo.put("pageSize", PAGE_SIZE);
            List<DepartmentModel> departmentModels = departmentService.selectList(mapInfo);
            map.put("list", departmentModels);
        }else {
            map.put("list",new ArrayList<DepartmentModel>());
        }
        return map;
    }
}
