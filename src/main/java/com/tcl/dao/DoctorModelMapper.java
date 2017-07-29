package com.tcl.dao;

import com.tcl.model.DoctorModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DoctorModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DoctorModel record);

    int insertSelective(DoctorModel record);

    DoctorModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DoctorModel record);

    int updateByPrimaryKeyWithBLOBs(DoctorModel record);

    int updateByPrimaryKey(DoctorModel record);

    List<DoctorModel> selectByType(Map map);

    List<DoctorModel> selectByPage(Map map);

    List<DoctorModel> queryDoctorByInfo(Map<String, Object> map);

    List<DoctorModel> queryPageDoctorByInfo(Map<String, Object> map);
}