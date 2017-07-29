package com.tcl.dao;

import com.tcl.model.HospitalModel;
import com.tcl.model.HospitalModelWithBLOBs;

import java.util.List;
import java.util.Map;

public interface HospitalModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HospitalModelWithBLOBs record);

    int insertSelective(HospitalModelWithBLOBs record);

    HospitalModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HospitalModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(HospitalModelWithBLOBs record);

    int updateByPrimaryKey(HospitalModel record);

    List<HospitalModel> selectByType(Map map);

    List<HospitalModel> selectByPageInfo(Map<String, Object> map);

    List<HospitalModel> queryByInfo(Map<String, Object> map);

    List<HospitalModel> queryByPageInfo(Map<String, Object> map);
}