package com.tcl.dao;

import com.tcl.model.PackageModel;
import com.tcl.model.PackageModelWithBLOBs;

public interface PackageModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PackageModelWithBLOBs record);

    int insertSelective(PackageModelWithBLOBs record);

    PackageModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PackageModelWithBLOBs record);

    int updateByPrimaryKey(PackageModel record);
}