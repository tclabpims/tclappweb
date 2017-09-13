package com.tcl.service;

import com.tcl.model.DoctorBillStatModel;
import com.tcl.model.DoctorTakeStatModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/9/3.
 */
public interface DoctorTakeStatService {

    List<DoctorTakeStatModel> selectList(Map<String, Object> map);

    void statDoctorTakeData();

    void cleanTable();
}
