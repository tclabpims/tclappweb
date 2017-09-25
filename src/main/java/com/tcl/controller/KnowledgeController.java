package com.tcl.controller;

import com.tcl.model.KnowledgeModel;
import com.tcl.model.KnowledgeModelWithBLOBs;
import com.tcl.service.KnowledgeService;
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
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeService knowledgeService;

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object> editKnowledge(KnowledgeModelWithBLOBs knowledgeModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        int result = 0;
        if (knowledgeModel.getId() != null) {
            result = knowledgeService.editKnowledge(knowledgeModel);
        } else {
            result = knowledgeService.addKnowledge(knowledgeModel);
        }
        if (result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }
}
