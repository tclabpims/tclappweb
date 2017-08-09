package com.tcl.service;

import com.tcl.model.PackageModel;
import com.tcl.model.PackageModelWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017-07-03.
 */
public interface PackageService {
    PackageModelWithBLOBs selectById(Long id);

    List<PackageModel> selectList(Map map);

    List<PackageModel> selectListByPage(Map map);

    List<PackageModel> queryPackage(Map map);

    List<PackageModel> queryListByPage(Map map);

    int deleteById(long id);

    int addPackage(PackageModelWithBLOBs packageModel);

    int updateById(PackageModelWithBLOBs packageMode);
}
