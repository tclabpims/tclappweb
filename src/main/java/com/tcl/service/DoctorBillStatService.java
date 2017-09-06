package com.tcl.service;

import com.tcl.model.DoctorBillStatModel;
import com.tcl.model.PackageSaleStatModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/9/3.
 */
public interface DoctorBillStatService {

    List<DoctorBillStatModel> selectList(Map<String, Object> map);

    void statDoctorBillData();

    void cleanTable();
}
