package com.tcl.dao;

import com.tcl.model.KnowledgeDetailsModel;
import com.tcl.model.KnowledgeDetailsModelWithBLOBs;

public interface KnowledgeDetailsModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnowledgeDetailsModelWithBLOBs record);

    int insertSelective(KnowledgeDetailsModelWithBLOBs record);

    KnowledgeDetailsModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnowledgeDetailsModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(KnowledgeDetailsModelWithBLOBs record);

    int updateByPrimaryKey(KnowledgeDetailsModel record);
}