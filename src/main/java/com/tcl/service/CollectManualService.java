package com.tcl.service;

import com.tcl.model.CollectManualModel;
import com.tcl.model.KnowledgeModelWithBLOBs;

/**
 * Created by LiuQi on 2017/9/24.
 */
public interface CollectManualService {

    CollectManualModel selectCollectManual(CollectManualModel collectManualModel);

    int editCollectManual(CollectManualModel collectManualModel);

    int addCollectManual(CollectManualModel collectManualModel);
}
