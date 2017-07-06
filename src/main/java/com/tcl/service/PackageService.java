package com.tcl.service;

import com.tcl.model.PackageModel;

import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2017-07-03.
 */
public interface PackageService {
    PackageModel selectById(Long id);

    List<PackageModel> selectList(Map map);
}
