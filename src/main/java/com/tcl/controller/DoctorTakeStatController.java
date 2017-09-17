package com.tcl.controller;

import com.tcl.model.DoctorBillStatModel;
import com.tcl.model.DoctorModel;
import com.tcl.model.DoctorTakeStatModel;
import com.tcl.service.DoctorBillStatService;
import com.tcl.service.DoctorService;
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

    @Autowired
    private DoctorService doctorService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping("list")
    public String dcotorTakeStatInfoList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Calendar calendar = Calendar.getInstance();
        mapInfo.put("year", Integer.toString(calendar.get(Calendar.YEAR)));
        mapInfo.put("takeDoctorName", "");
        mapInfo.put("month", "");
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("year", map_result.get("year"));
        map.put("list", map_result.get("list"));
        map.put("list_all", map_result.get("list_all"));
        return "saleStat/doctorTakeStat";
    }

    @RequestMapping("query")
    public String doctorTakeStatInfoPageList(ModelMap map, String pageNo, String takeDoctorName, String year, String month) {
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
        map.put("list_all", map_result.get("list_all"));
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
        if(!isEmpty) {
            map.put("start_num", (page_no - 1) * PAGE_SIZE);
            map.put("pageSize", PAGE_SIZE);
            List<DoctorTakeStatModel> list_for_page = doctorTakeStatService.selectList(map);
            if (!((String)map.get("year")).isEmpty() && ((String)map.get("takeDoctorName")).isEmpty() && ((String)map.get("month")).isEmpty()) {
                list_for_page.clear();
                List<DoctorModel> doctorModels = doctorService.queryByInfo("", null, "", "", "1", "2", null, null);
                for (DoctorModel doctorModel : doctorModels) {
                    DoctorTakeStatModel doctorTakeStatYear = new DoctorTakeStatModel();
                    Long totalNumber = 0L;
                    Long totalAmount = 0L;
                    for (DoctorTakeStatModel doctorTakeStatModel : list_for_all) {
                        if (doctorModel.getDoctorName().equals(doctorTakeStatModel.getTakeDoctorName())) {
                            totalNumber += doctorTakeStatModel.getTakeNum();
                            totalAmount += doctorTakeStatModel.getTakeAmount();
                        }
                    }
                    doctorTakeStatYear.setTakeNum(totalNumber);
                    doctorTakeStatYear.setTakeAmount(totalAmount);
                    doctorTakeStatYear.setTakeDoctorId(doctorModel.getId());
                    doctorTakeStatYear.setTakeDoctorName(doctorModel.getDoctorName());
                    doctorTakeStatYear.setYear((String) map.get("year"));
                    doctorTakeStatYear.setId(doctorModel.getId());
                    doctorTakeStatYear.setCreateTime(new Date());
                    list_for_page.add(doctorTakeStatYear);
                }
                list_for_all.clear();
                for (DoctorTakeStatModel doctorTakeStatModel: list_for_page) {
                    list_for_all.add(doctorTakeStatModel);
                }
                list_for_page.clear();
                if (page_no * PAGE_SIZE > list_for_all.size()) {
                    list_for_page = list_for_all.subList((page_no - 1) * PAGE_SIZE, list_for_all.size());
                } else {
                    list_for_page = list_for_all.subList((page_no - 1) * PAGE_SIZE, page_no * PAGE_SIZE);
                }
            }
            total_page = (list_for_all.size() + PAGE_SIZE - 1) / PAGE_SIZE;
            if (total_page < 1) {
                total_page = 1;
            }
            if(page_no > total_page) {
                page_no = total_page;
            }
            map.put("list", list_for_page);
            map.put("list_all", list_for_all);
            map.put("list", list_for_page);
            map.put("list_all", list_for_all);
        }else {
            map.put("list",new ArrayList<DoctorTakeStatModel>());
            map.put("list_all",new ArrayList<DoctorTakeStatModel>());
        }
        map.put("pageNo", page_no);
        map.put("totalPage", total_page);
        return map;
    }
}
