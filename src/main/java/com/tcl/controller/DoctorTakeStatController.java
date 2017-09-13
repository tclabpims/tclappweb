package com.tcl.controller;

import com.tcl.model.DoctorBillStatModel;
import com.tcl.model.DoctorTakeStatModel;
import com.tcl.service.DoctorBillStatService;
import com.tcl.service.DoctorTakeStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Controller
@RequestMapping("/doctorTakeStat")
public class DoctorTakeStatController {

    @Autowired
    private DoctorTakeStatService doctorTakeStatService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping("list")
    public String packageSaleStatInfoList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Calendar calendar = Calendar.getInstance();
        mapInfo.put("year", Integer.toString(calendar.get(Calendar.YEAR)));
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("year", map_result.get("year"));
        map.put("list", map_result.get("list"));
        return "saleStat/doctorTakeStat";
    }

    @RequestMapping("query")
    public String doctorBillStatInfoPageList(ModelMap map, String pageNo, String takeDoctorName, String year, String month) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("takeDoctorName", takeDoctorName.trim());
        mapInfo.put("year", year.trim());
        mapInfo.put("month", month.trim());
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("takeDoctorName", map_result.get("takeDoctorName"));
        map.put("year", year.trim());
        map.put("month", month.trim());
        map.put("list", map_result.get("list"));
        map.put("query_flag", true);
        return "saleStat/doctorTakeStat";
    }

    private Map<String, Object> getData(Map<String, Object> map) {
        int page_no;
        boolean isEmpty = false;
//        Map<String, Object> map = new HashMap<String, Object>();
        if (map.get("pageNo") == null || map.get("pageNo") == "") {
            page_no = 1;
        }
        else {
            page_no = Integer.parseInt(((String)map.get("pageNo")).trim());
            if (page_no < 1) {
                page_no = 1;
            }
        }
        List<DoctorTakeStatModel> list_for_all = doctorTakeStatService.selectList(map);
        int total_page = (list_for_all.size() + PAGE_SIZE - 1) / PAGE_SIZE;
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
            map.put("start_num", (page_no - 1) * PAGE_SIZE);
            map.put("pageSize", PAGE_SIZE);
            List<DoctorTakeStatModel> list_for_page = doctorTakeStatService.selectList(map);
            map.put("list", list_for_page);
        }else {
            map.put("list",new ArrayList<DoctorTakeStatModel>());
        }
        return map;
    }
}
