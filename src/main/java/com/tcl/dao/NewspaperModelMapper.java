package com.tcl.dao;

import com.tcl.model.NewspaperModel;

import java.util.List;
import java.util.Map;

public interface NewspaperModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewspaperModel record);

    int insertSelective(NewspaperModel record);

    NewspaperModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewspaperModel record);

    int updateByPrimaryKey(NewspaperModel record);

    List<NewspaperModel> selectByType(Map map);
}