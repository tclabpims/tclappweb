package com.tcl.dao;

import com.tcl.model.HospitalSaleStatModel;

import java.util.List;
import java.util.Map;

public interface HospitalSaleStatModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HospitalSaleStatModel record);

    int insertSelective(HospitalSaleStatModel record);

    HospitalSaleStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HospitalSaleStatModel record);

    int updateByPrimaryKey(HospitalSaleStatModel record);

    List<HospitalSaleStatModel> selectList(Map<String, Object> map);

    int batchInsert(List<HospitalSaleStatModel> list);

    void cleanTable();
}