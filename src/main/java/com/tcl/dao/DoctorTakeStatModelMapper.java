package com.tcl.dao;

import com.tcl.model.DoctorTakeStatModel;

import java.util.List;
import java.util.Map;

public interface DoctorTakeStatModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DoctorTakeStatModel record);

    int insertSelective(DoctorTakeStatModel record);

    DoctorTakeStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DoctorTakeStatModel record);

    int updateByPrimaryKey(DoctorTakeStatModel record);

    List<DoctorTakeStatModel> selectList(Map<String, Object> map);

    void cleanTable();

    void batchInsert(List<DoctorTakeStatModel> list);
}