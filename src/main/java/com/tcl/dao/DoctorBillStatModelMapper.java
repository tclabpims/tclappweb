package com.tcl.dao;

import com.tcl.model.DoctorBillStatModel;

import java.util.List;
import java.util.Map;

public interface DoctorBillStatModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DoctorBillStatModel record);

    int insertSelective(DoctorBillStatModel record);

    DoctorBillStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DoctorBillStatModel record);

    int updateByPrimaryKey(DoctorBillStatModel record);

    void cleanTable();

    void batchInsert(List<DoctorBillStatModel> list);

    List<DoctorBillStatModel> selectList(Map<String, Object> map);
}