package com.tcl.service;

import com.tcl.model.CollectManualModel;
import com.tcl.model.KnowledgeDetailsModelWithBLOBs;

/**
 * Created by LiuQi on 2017/9/24.
 */
public interface KnowledgeDetailsService {
    int editKnowledgeDetails(KnowledgeDetailsModelWithBLOBs knowledgeDetailsModel);

    int addKnowledgeDetails(KnowledgeDetailsModelWithBLOBs KnowledgeDetailsModelWithBLOBs);
}
