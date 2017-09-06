package com.tcl.service;

import com.tcl.model.HospitalSaleStatModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/9/3.
 */
public interface HospitalSaleStatService {

    List<HospitalSaleStatModel> selectList(Map<String, Object> map);

    void statHospitalSaleData();

    void cleanTable();
}
