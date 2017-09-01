package com.tcl.controller;

import com.tcl.model.*;
import com.tcl.service.FinaManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/29.
 */
@Controller
@RequestMapping("/finaManage")
public class FinaManageController {

    @Autowired
    private FinaManageService finaManageService;

    private static final int PAGE_SIZE = 8;

    /**
     * 套餐的销售情况
     * @param map
     * @param pageNo
     * @return
     */
    @RequestMapping("/packageSaleCondition")
    public String packageSaleCondition(ModelMap map, String pageNo) {
        Map<String, Object> mapHosInfo = new HashMap<String, Object>();
        List<PackageModelWithBLOBs> packageList =  finaManageService.packageList(mapHosInfo);
        Map<String, Object> mapOrInfo = new HashMap<String, Object>();
        List<OrderModelWithBLOBs> orderList = finaManageService.orderList(mapOrInfo);
        List<PackageSaleConModel> packageSaleConList = new ArrayList<PackageSaleConModel>();
        for(PackageModelWithBLOBs packageModel : packageList) {
            PackageSaleConModel packageSaleConModel = new PackageSaleConModel();
            packageSaleConModel.setPackageId(packageModel.getId());
            packageSaleConModel.setPackageName(packageModel.getName());
            Long id = packageModel.getId();
            //销售的总量
            long saleTotalNumber = 0;
            //销售额
            long saleSum = 0;
            for(OrderModelWithBLOBs orderModel : orderList) {
                if (orderList != null && orderModel.getPackageId() != null && id == orderModel.getPackageId())
                if (orderModel.getStatus() >= 1 ) {
                    saleTotalNumber++;
                    saleSum += orderModel.getPackageNum() * orderModel.getPrice();
                }
            }
            packageSaleConModel.setSaleTotalNumber(saleTotalNumber);
            packageSaleConModel.setSaleSum(saleSum);
            packageSaleConList.add(packageSaleConModel);
        }
        Map mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("paramList", packageSaleConList);
        Map result_map = getData(mapInfo);
        map.put("list", result_map.get("pageList"));
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        return "fiManage/packageSaleInfolist";
    }

    /**
     * 统计采集点的财务信息
     * @param map
     * @return
     */
    @RequestMapping("/hospitalFiStatistic")
    public String hospitalFiStatistic(ModelMap map, String pageNo) {
        Map<String, Object> mapHosInfo = new HashMap<String, Object>();
        List<HospitalModelWithBLOBs> hospitalList =  finaManageService.hospitalList(mapHosInfo);
        Map<String, Object> mapOrInfo = new HashMap<String, Object>();
        List<OrderModelWithBLOBs> orderList = finaManageService.orderList(mapOrInfo);
        List<HospitalFiManaModel> hospitalFiManaModelList = new ArrayList<HospitalFiManaModel>();
        for(HospitalModelWithBLOBs hospitalModel : hospitalList) {
            HospitalFiManaModel hospitalFiManaModel = new HospitalFiManaModel();
            hospitalFiManaModel.setId(hospitalModel.getId());
            hospitalFiManaModel.setHospitalName(hospitalModel.getName());
            Long id = hospitalModel.getId();
            //子订单数量
            long orderNumber = 0;
            //销售额
            long saleSum = 0;
            //销售的套餐数量
            long totalPackages = 0;
            for(OrderModelWithBLOBs orderModel : orderList) {
                if ( orderModel.getTradeModel() != null && id == orderModel.getTradeModel().getHospitalId()) {
                    if (orderModel.getStatus() >= 1 ) {
                        orderNumber++;
                        totalPackages += orderModel.getPackageNum();
                        saleSum += orderModel.getPackageNum() * orderModel.getPrice();
                    }
                }
            }
            hospitalFiManaModel.setOrderNumber(orderNumber);
            hospitalFiManaModel.setSaleSum(saleSum);
            hospitalFiManaModel.setTotalPackages(totalPackages);
            hospitalFiManaModelList.add(hospitalFiManaModel);
        }
        Map mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("paramList", hospitalFiManaModelList);
        Map result_map = getData(mapInfo);
        map.put("list", result_map.get("pageList"));
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        return "fiManage/hospitalFiInfolist";
    }

    private Map<String, Object> getData(Map<String, Object> mapInfo) {
        int page_no;
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
        List paramList = (List)mapInfo.get("paramList");
        int total_page = (paramList.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        if (total_page < 1) {
            total_page = 1;
        }
        if(page_no > total_page) {
            page_no = total_page;
        }
        map.put("pageNo", page_no);
        map.put("totalPage", total_page);
        if ((page_no * PAGE_SIZE) > paramList.size()) {
            List pageList = paramList.subList((page_no - 1) * PAGE_SIZE, paramList.size());
            map.put("pageList", pageList);
        } else {
            List pageList = paramList.subList((page_no - 1) * PAGE_SIZE, page_no * PAGE_SIZE);
            map.put("pageList", pageList);
        }
        return map;
    }
}


