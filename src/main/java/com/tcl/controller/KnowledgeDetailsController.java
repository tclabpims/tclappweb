package com.tcl.controller;

import com.tcl.model.CollectManualModel;
import com.tcl.model.KnowledgeDetailsModelWithBLOBs;
import com.tcl.service.CollectManualService;
import com.tcl.service.KnowledgeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuQi on 2017/9/24.
 */
@Controller
@RequestMapping("/knowledgeDetails")
public class KnowledgeDetailsController {

    @Autowired
    KnowledgeDetailsService knowledgeDetailsService;

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> editKnowledgeDetails(KnowledgeDetailsModelWithBLOBs knowledgeDetailsModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = 0;
        if (knowledgeDetailsModel.getId() != null) {
            result = knowledgeDetailsService.editKnowledgeDetails(knowledgeDetailsModel);
        } else {
            result = knowledgeDetailsService.addKnowledgeDetails(knowledgeDetailsModel);
        }
        if (result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }
}
