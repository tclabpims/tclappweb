package com.tcl.dao;

import com.tcl.model.PackageDetailsModel;

public interface PackageDetailsModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PackageDetailsModel record);

    int insertSelective(PackageDetailsModel record);

    PackageDetailsModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageDetailsModel record);

    int updateByPrimaryKey(PackageDetailsModel record);
}