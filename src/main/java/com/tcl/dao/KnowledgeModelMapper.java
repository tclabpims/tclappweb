package com.tcl.dao;

import com.tcl.model.KnowledgeModel;
import com.tcl.model.KnowledgeModelWithBLOBs;

public interface KnowledgeModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnowledgeModelWithBLOBs record);

    int insertSelective(KnowledgeModelWithBLOBs record);

    KnowledgeModelWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnowledgeModelWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(KnowledgeModelWithBLOBs record);

    int updateByPrimaryKey(KnowledgeModel record);

    KnowledgeModelWithBLOBs selectKnowledge(KnowledgeModel knowledgeModel);
}