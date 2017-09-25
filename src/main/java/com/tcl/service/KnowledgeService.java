package com.tcl.service;

import com.tcl.model.KnowledgeModel;
import com.tcl.model.KnowledgeModelWithBLOBs;

/**
 * Created by LiuQi on 2017/9/24.
 */
public interface KnowledgeService {

    KnowledgeModelWithBLOBs selectKnowledge(KnowledgeModelWithBLOBs knowledgeModel);

    int editKnowledge(KnowledgeModelWithBLOBs knowledgeModel);

    int addKnowledge(KnowledgeModelWithBLOBs knowledgeModel);
}
