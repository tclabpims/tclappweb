package com.tcl.controller;

import com.tcl.model.HospitalSaleStatModel;
import com.tcl.model.PackageModelWithBLOBs;
import com.tcl.model.PackageSaleStatModel;
import com.tcl.service.HospitalSaleStatService;
import com.tcl.service.PackageSaleStatService;
import com.tcl.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by LiuQi on 2017/9/3.
 */
@Controller
@RequestMapping("/packageSaleStat")
public class PackageSaleStatController {

    @Autowired
    private PackageSaleStatService packageSaleStatService;

    @Autowired
    private PackageService packageService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping("list")
    public String packageSaleStatInfoList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Calendar calendar = Calendar.getInstance();
        mapInfo.put("year", Integer.toString(calendar.get(Calendar.YEAR)));
        mapInfo.put("packageName", "");
        mapInfo.put("month", "");
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("year", map_result.get("year"));
        map.put("list", map_result.get("list"));
        map.put("list_all", map_result.get("list_all"));
        return "saleStat/packageSaleStat";
    }

    @RequestMapping("query")
    public String hospitalSaleStatInfoPageList(ModelMap map, String pageNo, String packageName, String year, String month) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("packageName", packageName.trim());
        mapInfo.put("year", year.trim());
        mapInfo.put("month", month.trim());
        Map<String, Object> map_result = getData(mapInfo);
        map.put("pageNo", map_result.get("pageNo"));
        map.put("totalPage", map_result.get("totalPage"));
        map.put("packageName", map_result.get("packageName"));
        map.put("year", year.trim());
        map.put("month", month.trim());
        map.put("list", map_result.get("list"));
        map.put("list_all", map_result.get("list_all"));
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
        if(!isEmpty) {
            map.put("start_num", (page_no - 1) * PAGE_SIZE);
            map.put("pageSize", PAGE_SIZE);
            List<PackageSaleStatModel> list_for_page = packageSaleStatService.selectList(map);
            if (!((String)map.get("year")).isEmpty() && ((String)map.get("packageName")).isEmpty() && ((String)map.get("month")).isEmpty()) {
                list_for_page.clear();
                List<PackageModelWithBLOBs> packageModels = packageService.selectList(new HashMap());
                for (PackageModelWithBLOBs packageModel : packageModels) {
                    PackageSaleStatModel packageSaleStatYear = new PackageSaleStatModel();
                    Long totalNumber = 0L;
                    Long totalAmount = 0L;
                    for (PackageSaleStatModel packageSaleStatModel : list_for_all) {
                        if (packageModel.getName().equals(packageSaleStatModel.getPackageName())) {
                            totalNumber += packageSaleStatModel.getSalesNum();
                            totalAmount += packageSaleStatModel.getSalesAmount();
                        }
                    }
                    packageSaleStatYear.setSalesNum(totalNumber);
                    packageSaleStatYear.setSalesAmount(totalAmount);
                    packageSaleStatYear.setPackageId(packageModel.getId());
                    packageSaleStatYear.setPackageName(packageModel.getName());
                    packageSaleStatYear.setPackagePrice(packageModel.getPrice());
                    packageSaleStatYear.setYear((String) map.get("year"));
                    packageSaleStatYear.setId(packageModel.getId());
                    packageSaleStatYear.setCreateTime(new Date());
                    list_for_page.add(packageSaleStatYear);
                }
                list_for_all.clear();
                for (PackageSaleStatModel packageSaleStatModel: list_for_page) {
                    list_for_all.add(packageSaleStatModel);
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
        }else {
            map.put("list",new ArrayList<PackageSaleStatModel>());
            map.put("list_all",new ArrayList<PackageSaleStatModel>());
        }
        map.put("pageNo", page_no);
        map.put("totalPage", total_page);
        return map;
    }
}
