package com.tcl.dao;

import com.tcl.model.OrderModel;
import com.tcl.model.OrderModelWithBLOBs;

import java.util.List;
import java.util.Map;

public interface OrderModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderModelWithBLOBs record);

    int insertSelective(OrderModelWithBLOBs record);

    OrderModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrderModelWithBLOBs record);

    int updateByPrimaryKey(OrderModel record);

    List<OrderModelWithBLOBs> selectList(Map map);
}