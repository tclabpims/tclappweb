package com.tcl.service.impl;

import com.tcl.dao.KnowledgeModelMapper;
import com.tcl.model.KnowledgeModel;
import com.tcl.model.KnowledgeModelWithBLOBs;
import com.tcl.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuQi on 2017/9/24.
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeModelMapper knowledgeDao;

    public int editKnowledge(KnowledgeModelWithBLOBs knowledgeModel) {
        return knowledgeDao.updateByPrimaryKeySelective(knowledgeModel);
    }

    public KnowledgeModelWithBLOBs selectKnowledge(KnowledgeModelWithBLOBs knowledgeModel) {
        return knowledgeDao.selectKnowledge(knowledgeModel);
    }

    public int addKnowledge(KnowledgeModelWithBLOBs knowledgeModel) {
        return knowledgeDao.insertSelective(knowledgeModel);
    }
}
