package com.tcl.dao;

import com.tcl.model.BillModel;

public interface BillModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillModel record);

    int insertSelective(BillModel record);

    BillModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillModel record);

    int updateByPrimaryKey(BillModel record);
}