package com.tcl.controller;

import com.sun.media.sound.ModelDirector;
import com.tcl.model.DoctorModel;
import com.tcl.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuQi on 2017/7/6.
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * 取出数据库中的医生
     * @param map
     * @param httpServletRequest
     * @param type
     * @return
     */
    @RequestMapping("/list")
    public String listDoctor(ModelMap map, HttpServletRequest httpServletRequest, @RequestParam String type) {
        map.put("list", doctorService.selectByType(type));
        return "doctor/list";
    }

    /**
     * 通过Id删除一位医生
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteById(String id){
        //对接收到的字符串参数进行基本的处理与判断
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        int result;
        Map<String, String> map = new HashMap<String, String>();
        if(matcher.matches()) {
            result = doctorService.deleteById(Long.parseLong(id));
            if(result > 0) {
                //返回1表示删除成功
                map.put("msg", "1");
            } else {
                //返回2表示删除失败
                map.put("msg", "2");
            }
        } else {
            //返回3表示参数有误
            map.put("msg", "3");
        }
        return map;
    }

    /**
     * 通过id得到医生的基本信息
     * @param id
     * @return
     */
    @RequestMapping(value="/getInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, DoctorModel> selectById(String id) {
        //对接收到的字符串参数进行基本的处理与判断
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        Map<String, DoctorModel> map = new HashMap<String, DoctorModel>();
        if (matcher.matches()) {
            map.put("doctor", doctorService.selectById(Long.parseLong(id)));
        } else {
            DoctorModel doctorModel = new DoctorModel();
            doctorModel.setUserName("paramIsError");
            map.put("doctor", doctorModel);
        }
        return map;
    }

    /**
     * 通过表单提交的数据更新医生的基本信息
     * @param doctorModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateById(DoctorModel doctorModel) {
        doctorModel.setModifyTime(new Date());
        int result = doctorService.updateById(doctorModel);
        Map<String, String> map = new HashMap<String, String>();
        if(result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 审核医生
     * @param id
     * @param status
     * @param auditReason
     * @return
     */
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateDoctorStatus(String id, String status, String auditReason) {
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        Map<String, String> map = new HashMap<String, String>();
        DoctorModel doctorModel = null;
        if(matcher.matches()) {
            doctorModel = doctorService.selectById(Long.parseLong(id));
            if(doctorModel != null) {
                doctorModel.setStatus(status);
                doctorModel.setAuditReason(auditReason);
                int result = doctorService.updateById(doctorModel);
                if (result > 0) {
                    map.put("msg", "success");
                    return map;
                } else {
                    map.put("msg", "failed");
                    return map;
                }
            }
            map.put("msg", "id error");
            return map;
        } else {
            map.put("msg", "param error");
            return map;
        }
    }

    /**
     * 增加医生
     * @return
     */
    @RequestMapping(value = "/add")
    public String addDoctor() {
        return "doctor/add";
    }
}
