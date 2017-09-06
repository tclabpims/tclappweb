package com.tcl.dao;

import com.tcl.model.HospitalSaleStatModel;
import com.tcl.model.PackageSaleStatModel;

import java.util.List;
import java.util.Map;

public interface PackageSaleStatModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PackageSaleStatModel record);

    int insertSelective(PackageSaleStatModel record);

    PackageSaleStatModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageSaleStatModel record);

    int updateByPrimaryKey(PackageSaleStatModel record);

    List<PackageSaleStatModel> selectList(Map<String, Object> map);

    void cleanTable();

    void batchInsert(List<PackageSaleStatModel> list);
}