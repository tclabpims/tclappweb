package com.tcl.dao;

import com.tcl.model.OrderModel;
import com.tcl.model.OrderModelWithBLOBs;

public interface OrderModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderModelWithBLOBs record);

    int insertSelective(OrderModelWithBLOBs record);

    OrderModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrderModelWithBLOBs record);

    int updateByPrimaryKey(OrderModel record);
}