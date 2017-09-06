package com.tcl.controller;

import com.tcl.model.HospitalSaleStatModel;
import com.tcl.model.PackageSaleStatModel;
import com.tcl.service.HospitalSaleStatService;
import com.tcl.service.PackageSaleStatService;
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
@RequestMapping("/packageSaleStat")
public class PackageSaleStatController {

    @Autowired
    private PackageSaleStatService packageSaleStatService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping("list")
    public String packageSaleStatInfoList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("list", map_result.get("list"));
        return "saleStat/packageSaleStat";
    }

    @RequestMapping("query")
    public String hospitalSaleStatInfoPageList(ModelMap map, String pageNo, String packageName, String timeStart, String timeEnd) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("packageName", packageName.trim());
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
        map.put("packageName", map_result.get("packageName"));
        map.put("timeStart", timeStart.trim());
        map.put("timeEnd", timeEnd.trim());
        map.put("list", map_result.get("list"));
        map.put("query_flag", true);
        return "saleStat/packageSaleStat";
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
        List<PackageSaleStatModel> list_for_all = packageSaleStatService.selectList(map);
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
            List<PackageSaleStatModel> list_for_page = packageSaleStatService.selectList(map);
            map.put("list", list_for_page);
        }else {
            map.put("list",new ArrayList<PackageSaleStatModel>());
        }
        return map;
    }
}
