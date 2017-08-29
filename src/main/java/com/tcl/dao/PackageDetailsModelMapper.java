package com.tcl.dao;

import com.tcl.model.PackageDetailsModel;

import java.util.List;
import java.util.Map;

public interface PackageDetailsModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PackageDetailsModel record);

    int insertSelective(PackageDetailsModel record);

    PackageDetailsModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageDetailsModel record);

    int updateByPrimaryKey(PackageDetailsModel record);

    List<PackageDetailsModel> selectList(Map map);

    void batchInsert(List<PackageDetailsModel> userLists);
}