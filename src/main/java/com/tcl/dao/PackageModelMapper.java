package com.tcl.dao;

import com.tcl.model.PackageModel;
import com.tcl.model.PackageModelWithBLOBs;

import java.util.List;
import java.util.Map;

public interface PackageModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PackageModelWithBLOBs record);

    int insertSelective(PackageModelWithBLOBs record);

    PackageModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackageModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PackageModelWithBLOBs record);

    int updateByPrimaryKey(PackageModel record);

    List<PackageModelWithBLOBs> selectList(Map map);

    List<PackageModel> selectListByPage(Map map);

    List<PackageModel> queryPackage(Map map);

    List<PackageModel> queryListByPage(Map map);

    void batchInsert(List<PackageModelWithBLOBs> packageList);
}