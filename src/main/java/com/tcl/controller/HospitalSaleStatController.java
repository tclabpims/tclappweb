package com.tcl.controller;

import com.tcl.model.HospitalModel;
import com.tcl.model.HospitalModelWithBLOBs;
import com.tcl.model.HospitalSaleStatModel;
import com.tcl.service.HospitalSaleStatService;
import com.tcl.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Controller
@RequestMapping("/hospitalSaleStat")
public class HospitalSaleStatController {

    @Autowired
    private HospitalSaleStatService hospitalSaleStatService;

    @Autowired
    private HospitalService hospitalService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping("list")
    public String hospitalSaleStatInfoList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Calendar calendar = Calendar.getInstance();
        mapInfo.put("year", Integer.toString(calendar.get(Calendar.YEAR)));
        mapInfo.put("month", "");
        mapInfo.put("hospitalName", "");
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("year", map_result.get("year"));
        map.put("list", map_result.get("list"));
        map.put("list_all", map_result.get("list_all"));
        return "saleStat/hospitalSaleStat";
    }

    @RequestMapping("query")
    public String hospitalSaleStatInfoPageList(ModelMap map, String pageNo, String hospitalName, String year, String month) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("hospitalName", hospitalName.trim());
        mapInfo.put("year", year.trim());
        mapInfo.put("month", month.trim());
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("hospitalName", map_result.get("hospitalName"));
        map.put("year", map_result.get("year"));
        map.put("month", month.trim());
        map.put("list", map_result.get("list"));
        map.put("list_all", map_result.get("list_all"));
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
        if(!isEmpty) {
            map.put("start_num", (page_no - 1) * PAGE_SIZE);
            map.put("pageSize", PAGE_SIZE);
            List<HospitalSaleStatModel> list_for_page = hospitalSaleStatService.selectList(map);
            if (!((String)map.get("year")).isEmpty() && ((String)map.get("hospitalName")).isEmpty() && ((String)map.get("month")).isEmpty()) {
                list_for_page.clear();
                List<HospitalModelWithBLOBs> hospitalModels = hospitalService.selectByType("");
                for (HospitalModelWithBLOBs hospitalModel : hospitalModels) {
                    HospitalSaleStatModel hospitalSaleStatYear = new HospitalSaleStatModel();
                    Long totalNumber = 0L;
                    Long totalAmount = 0L;
                    for (HospitalSaleStatModel hospitalSaleStatModel : list_for_all) {
                        if (hospitalModel.getName().equals(hospitalSaleStatModel.getHospitalName())) {
                            totalNumber += hospitalSaleStatModel.getSalesNum();
                            totalAmount += hospitalSaleStatModel.getSalesAmount();
                        }
                    }
                    hospitalSaleStatYear.setSalesNum(totalNumber);
                    hospitalSaleStatYear.setSalesAmount(totalAmount);
                    hospitalSaleStatYear.setHospitalId(hospitalModel.getId());
                    hospitalSaleStatYear.setHospitalName(hospitalModel.getName());
                    hospitalSaleStatYear.setYear((String) map.get("year"));
                    hospitalSaleStatYear.setId(hospitalModel.getId());
                    hospitalSaleStatYear.setCreateTime(new Date());
                    list_for_page.add(hospitalSaleStatYear);
                }
                list_for_all.clear();
                for (HospitalSaleStatModel hospitalSaleStatModel: list_for_page) {
                    list_for_all.add(hospitalSaleStatModel);
                }
                list_for_page.clear();
                if (page_no * PAGE_SIZE > list_for_all.size()) {
                    list_for_page = list_for_all.subList((page_no - 1) * PAGE_SIZE, list_for_all.size());
                } else {
                    list_for_page = list_for_all.subList((page_no - 1) * PAGE_SIZE, page_no * PAGE_SIZE);
                }
            }
            map.put("list", list_for_page);
            map.put("list_all", list_for_all);
            total_page = (list_for_all.size() + PAGE_SIZE - 1) / PAGE_SIZE;
            if (total_page < 1) {
                total_page = 1;
            }
            if(page_no > total_page) {
                page_no = total_page;
            }
        }else {
            map.put("list",new ArrayList<HospitalSaleStatModel>());
            map.put("list_all",new ArrayList<HospitalSaleStatModel>());
        }
        map.put("pageNo", page_no);
        map.put("totalPage", total_page);
        return map;
    }
}
