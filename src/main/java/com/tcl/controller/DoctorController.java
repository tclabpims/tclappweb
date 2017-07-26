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
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuQi on 2017/7/6.
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {

    //每页显示的条目数量
    private static final int PAGE_SIZE = 8;

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
    public String listDoctor(ModelMap map, HttpServletRequest httpServletRequest,
                             @RequestParam(required = false) String type, @RequestParam(required = false) String pageNo) {
        int page_no;
        if(pageNo == null || pageNo == "") {
            page_no = 1;
        }else {
            page_no = Integer.parseInt(pageNo.trim());
            if (page_no < 1) {
                page_no = 1;
            }
        }
        List<DoctorModel> doctor_list_all = doctorService.selectByType(type);
        int total_records = doctor_list_all.size();
        int total_page = (total_records + PAGE_SIZE - 1) / PAGE_SIZE;
        if(total_page < 1) {
            total_page = 1;
        }
        if (page_no > total_page) {
            page_no = total_page;
        }
        List<DoctorModel> doctor_list = doctorService.selectByPage(type, page_no, PAGE_SIZE);
        map.put("pageNo", page_no);
        map.put("list", doctor_list);
        map.put("totalPage", total_page);
        return "doctor/list";
    }
    /**
     * 查询医生
     * @param userName
     * @param hospitalId
     * @param doctorName
     * @param title
     * @param status
     * @param type
     * @param createTimeStart
     * @param createTimeEnd
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryDoctors(ModelMap map,
                               String userName,
                               String hospitalId,
                               String doctorName,
                               String title,
                               String status,
                               String type,
                               String createTimeStart,
                               String createTimeEnd,
                               String pageNo) {
        int page_no;
        if(pageNo == null || pageNo == "") {
            page_no = 1;
        }else {
            page_no = Integer.parseInt(pageNo.trim());
            if (page_no < 1) {
                page_no = 1;
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date create_time_start = null;
        Date create_time_end = null;
        Long hospital_id;
        try{
            create_time_start = dateFormat.parse(createTimeStart.trim());
            create_time_end = dateFormat.parse(createTimeEnd.trim());
        }catch (Exception e) {
            e.getStackTrace();
        }
        if(hospitalId.trim() == null || hospitalId.trim() == "") {
            hospital_id = null;
        }else {
            hospital_id = Long.parseLong(hospitalId.trim());
        }
        List<DoctorModel> doctors_all =  doctorService.queryByInfo(userName.trim(), hospital_id, doctorName.trim(),
                title.trim(), status.trim(), type.trim(), create_time_start, create_time_end);

        int totalPage = (doctors_all.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        if(totalPage < 1) {
            totalPage = 1;
        }
        if(page_no > totalPage) {
            page_no = totalPage;
        }
        if (totalPage > 0) {
            List<DoctorModel> doctors =  doctorService.queryByInfoPgae(userName.trim(), hospital_id, doctorName.trim(),
                    title.trim(), status.trim(), type.trim(), create_time_start, create_time_end, page_no, PAGE_SIZE);
            map.put("list", doctors);
        }
        map.put("userName_", userName.trim());
        map.put("hospital_id", hospital_id);
        map.put("doctorName", doctorName.trim());
        map.put("title", title.trim());
        map.put("status", status.trim());
        map.put("type", type.trim());
        map.put("createTimeStart", createTimeStart.trim());
        map.put("createTimeEnd", createTimeEnd.trim());
        map.put("pageNo", page_no);
        map.put("query_flag", true);
        map.put("totalPage", totalPage);
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
     * @param doctorModel
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addDoctor(DoctorModel doctorModel) {
        doctorModel.setCreateTime(new Date());
        doctorModel.setReadReportNum(0);
        doctorModel.setDiagnosisNum(0);
        doctorModel.setIsOpenAutoreceipt("NO");
        doctorModel.setReceiptInterval(0);
        int result = doctorService.addADoctor(doctorModel);
        Map<String, String> map = new HashMap<String, String>();
        if(result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }
}
