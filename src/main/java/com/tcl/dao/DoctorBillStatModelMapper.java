package com.tcl.dao;

import com.tcl.model.DoctorBillStatModel;

public interface DoctorBillStatModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DoctorBillStatModel record);

    int insertSelective(DoctorBillStatModel record);

    DoctorBillStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DoctorBillStatModel record);

    int updateByPrimaryKey(DoctorBillStatModel record);
}