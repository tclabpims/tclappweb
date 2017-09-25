package com.tcl.service.impl;

import com.tcl.dao.CollectManualModelMapper;
import com.tcl.dao.KnowledgeDetailsModelMapper;
import com.tcl.model.CollectManualModel;
import com.tcl.model.KnowledgeDetailsModelWithBLOBs;
import com.tcl.service.CollectManualService;
import com.tcl.service.KnowledgeDetailsService;
import com.tcl.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuQi on 2017/9/24.
 */
@Service
public class KnowledgeDetailsServiceImpl implements KnowledgeDetailsService {

    @Autowired
    private KnowledgeDetailsModelMapper knowledgeDetailsDao;

    public int editKnowledgeDetails(KnowledgeDetailsModelWithBLOBs knowledgeDetailsModel) {
        return knowledgeDetailsDao.updateByPrimaryKeySelective(knowledgeDetailsModel);
    }

    public int addKnowledgeDetails(KnowledgeDetailsModelWithBLOBs knowledgeDetailsModel) {
        return knowledgeDetailsDao.insertSelective(knowledgeDetailsModel);
    }
}
