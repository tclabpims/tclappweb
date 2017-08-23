package com.tcl.dao;

import com.tcl.model.TradeModel;

import java.util.List;
import java.util.Map;

public interface TradeModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeModel record);

    int insertSelective(TradeModel record);

    TradeModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeModel record);

    int updateByPrimaryKey(TradeModel record);

    List<TradeModel> selectList(Map map);
}