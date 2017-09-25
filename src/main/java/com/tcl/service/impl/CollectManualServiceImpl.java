package com.tcl.service.impl;

import com.tcl.dao.CollectManualModelMapper;
import com.tcl.dao.KnowledgeModelMapper;
import com.tcl.model.CollectManualModel;
import com.tcl.model.KnowledgeModelWithBLOBs;
import com.tcl.service.CollectManualService;
import com.tcl.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiuQi on 2017/9/24.
 */
@Service
public class CollectManualServiceImpl implements CollectManualService {

    @Autowired
    private CollectManualModelMapper collectManualDao;

    public CollectManualModel selectCollectManual(CollectManualModel collectManualModel) {
        return null;
    }

    public int editCollectManual(CollectManualModel collectManualModel) {
        return collectManualDao.updateByPrimaryKeySelective(collectManualModel);
    }

    public int addCollectManual(CollectManualModel collectManualModel) {
        return collectManualDao.insertSelective(collectManualModel);
    }
}
