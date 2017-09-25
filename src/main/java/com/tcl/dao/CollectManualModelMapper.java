package com.tcl.dao;

import com.tcl.model.CollectManualModel;

public interface CollectManualModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollectManualModel record);

    int insertSelective(CollectManualModel record);

    CollectManualModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectManualModel record);

    int updateByPrimaryKeyWithBLOBs(CollectManualModel record);

    int updateByPrimaryKey(CollectManualModel record);
}