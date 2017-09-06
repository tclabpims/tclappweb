package com.tcl.controller;

import com.tcl.model.HospitalSaleStatModel;
import com.tcl.service.HospitalSaleStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Controller
@RequestMapping("/hospitalSaleStat")
public class HospitalSaleStatController {

    @Autowired
    private HospitalSaleStatService hospitalSaleStatService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping("list")
    public String hospitalSaleStatInfoList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("list", map_result.get("list"));
        return "saleStat/hospitalSaleStat";
    }

    @RequestMapping("query")
    public String hospitalSaleStatInfoPageList(ModelMap map, String pageNo, String hospitalName, String timeStart, String timeEnd) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("hospitalName", hospitalName.trim());
        if (timeStart != null && !timeStart.trim().isEmpty()) {
            mapInfo.put("start_year", timeStart.substring(0, 4));
            mapInfo.put("start_month", Integer.toString(Integer.parseInt(timeStart.substring(5))));
        }
        if (timeEnd != null && !timeEnd.trim().isEmpty()) {
            mapInfo.put("end_year", timeEnd.substring(0, 4));
            mapInfo.put("end_month", Integer.toString(Integer.parseInt(timeEnd.substring(5))));
        }
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("hospitalName", map_result.get("hospitalName"));
        map.put("timeStart", timeStart.trim());
        map.put("timeEnd", timeEnd.trim());
        map.put("list", map_result.get("list"));
        map.put("query_flag", true);
        return "saleStat/hospitalSaleStat";
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
        List<HospitalSaleStatModel> list_for_all = hospitalSaleStatService.selectList(map);
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
            List<HospitalSaleStatModel> list_for_page = hospitalSaleStatService.selectList(map);
            map.put("list", list_for_page);
        }else {
            map.put("list",new ArrayList<HospitalSaleStatModel>());
        }
        return map;
    }
}
