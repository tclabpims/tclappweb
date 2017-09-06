package com.tcl.service;

import com.tcl.model.HospitalSaleStatModel;
import com.tcl.model.PackageSaleStatModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/9/3.
 */
public interface PackageSaleStatService {

    List<PackageSaleStatModel> selectList(Map<String, Object> map);

    void statPackageSaleData();

    void cleanTable();
}
