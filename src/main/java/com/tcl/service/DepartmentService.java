package com.tcl.service;

import com.tcl.model.DepartmentModel;
import com.tcl.model.PackageDetailsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
public interface DepartmentService {

    List<DepartmentModel> selectList(Map map);

    int addPackageDetails(DepartmentModel departmentModel);

    DepartmentModel selectById(long id);

    int updateById(DepartmentModel departmentModel);

    int deleteById(long id);
}
