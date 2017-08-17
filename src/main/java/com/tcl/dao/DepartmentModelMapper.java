package com.tcl.dao;

import com.tcl.model.DepartmentModel;

import java.util.List;
import java.util.Map;

public interface DepartmentModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartmentModel record);

    int insertSelective(DepartmentModel record);

    DepartmentModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartmentModel record);

    int updateByPrimaryKey(DepartmentModel record);

    List<DepartmentModel> selectList(Map map);
}