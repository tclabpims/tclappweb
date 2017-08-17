package com.tcl.controller;

import com.tcl.model.DoctorModel;
import com.tcl.service.DoctorService;
import com.tcl.utils.StringUtil;
import com.tcl.utils.JMessageUtils;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
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
        boolean isEmpty = false;
        if(pageNo == null || pageNo == "") {
            page_no = 1;
        }else {
            page_no = Integer.parseInt(pageNo.trim());
            if (page_no < 1) {
                page_no = 1;
            }
        }
        List<DoctorModel> doctor_list_all = doctorService.selectByType(type.trim());
        int total_records = doctor_list_all.size();
        int total_page = (total_records + PAGE_SIZE - 1) / PAGE_SIZE;
        if(total_page < 1) {
            total_page = 1;
            isEmpty = true;
        }
        if (page_no > total_page) {
            page_no = total_page;
        }
        if (!isEmpty) {
            List<DoctorModel> doctor_list = doctorService.selectByPage(type, page_no, PAGE_SIZE);
            map.put("list", doctor_list);
        } else {
            map.put("list", new ArrayList<DoctorModel>());
        }
        map.put("type", type.trim());
        map.put("pageNo", page_no);
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
        boolean isEmpty = false;
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
            isEmpty = true;
        }
        if(page_no > totalPage) {
            page_no = totalPage;
        }
        if (!isEmpty) {
            List<DoctorModel> doctors =  doctorService.queryByInfoPgae(userName.trim(), hospital_id, doctorName.trim(),
                    title.trim(), status.trim(), type.trim(), create_time_start, create_time_end, page_no, PAGE_SIZE);
            map.put("list", doctors);
        } else {
            map.put("list", new ArrayList<DoctorModel>());
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
        if(matcher.matches()) {
            DoctorModel doctorModel = new DoctorModel();
            doctorModel.setId(Long.parseLong(id));
            doctorModel.setStatus(status.trim());
            doctorModel.setAuditReason(auditReason.trim());
            int result = doctorService.updateById(doctorModel);
            if (result > 0) {
                //更新成功后将该医生在极光上注册，实现聊天
                if (status.trim().equals("1")) {
                    DoctorModel doctor = doctorService.selectById(Long.parseLong(id));
                    JMessageUtils.registerUser(doctor.getUserName());
                }
                map.put("msg", "success");
                return map;
            } else {
                map.put("msg", "failed");
                return map;
            }
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
        doctorModel.setPassWord(StringUtil.MD5(doctorModel.getPassWord()));
        int result = doctorService.addADoctor(doctorModel);
        Map<String, String> map = new HashMap<String, String>();
        if(result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 导出Excel表格
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportexcel", method = RequestMethod.POST)
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DoctorModel> doctor_list = null;
        //标题行
        String title[] = {"医生Id", "用户名", "医生姓名", "身份证号码","医院id", "医院名称", "科室编号", "科室名称", "性别", "年龄",
                "职称", "从业资格证号码", "职称号码", "学历", "职位", "解读报告次数", "服务次数", "状态", "岗位", "验证码", "验证码发送时间",
                "是否开启自动接单", "自动接单间隔（分钟）", "最后一次登录时间", "创建时间", "修改时间","审核原因", "介绍"};
        StringBuilder tempPath = new StringBuilder();
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddkkmmss_S");
        tempPath.append(fileNameFormat.format(new Date()));
        tempPath.append(".").append("xls");
        String filename = tempPath.toString();
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//			response.setHeader("Content-Disposition", "attachment;filename="+ new String((path1).getBytes(), "iso-8859-1"));
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename).getBytes(), "utf-8"));
            OutputStream os = response.getOutputStream();
            WritableWorkbook book = Workbook.createWorkbook(os);
            WritableSheet sheet = book.createSheet("套餐信息", 0);
            //写入标题
            for (int i=0; i<title.length; i++) {
                sheet.addCell(new Label(i, 0, title[i]));
            }
            doctor_list = doctorService.selectByType("");
            for (int i=0; i<doctor_list.size(); i++) {
                int j=0;
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getId() != null ? Long.toString(doctor_list.get(i).getId()) : ""));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getUserName()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getDoctorName()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getSfzNum()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getHospitalId() != null ? Long.toString(doctor_list.get(i).getHospitalId()) : ""));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getHospitalName()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getDepartmentNum()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getDepartmentName()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getSex()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getAge() != null ? Long.toString(doctor_list.get(i).getAge()) : ""));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getTitle()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getZzNum()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getZcNum()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getEducation()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getPosition()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getReadReportNum() != null ? Long.toString(doctor_list.get(i).getReadReportNum()) : ""));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getDiagnosisNum() != null ? Long.toString(doctor_list.get(i).getDiagnosisNum()) : ""));
                if (doctor_list.get(i).getStatus().equals("0")) {
                    sheet.addCell(new Label(j++, i+1, "初始化"));
                } else if (doctor_list.get(i).getStatus().equals("1")) {
                    sheet.addCell(new Label(j++, i+1, "可用"));
                } else if (doctor_list.get(i).getStatus().equals("2")) {
                    sheet.addCell(new Label(j++, i+1, "待审核"));
                } else {
                    sheet.addCell(new Label(j++, i+1, "停用"));
                }
                if (doctor_list.get(i).getStatus().equals("1")) {
                    sheet.addCell(new Label(j++, i+1, "医生"));
                } else if (doctor_list.get(i).getStatus().equals("2")) {
                    sheet.addCell(new Label(j++, i+1, "护士"));
                } else {
                    sheet.addCell(new Label(j++, i+1, "暂无"));
                }
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getVerificationCode()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getCodeSendTime() != null ? StringUtil.getFormatDate(doctor_list.get(i).getCodeSendTime()) : ""));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getIsOpenAutoreceipt()));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getReceiptInterval() != null ? Integer.toString(doctor_list.get(i).getReceiptInterval()) : ""));
                sheet.addCell(new Label(j++, i+1, doctor_list.get(i).getLastLoginTime() != null ? StringUtil.getFormatDate(doctor_list.get(i).getLastLoginTime()) : ""));
                sheet.addCell(new Label(j++, i + 1, doctor_list.get(i).getCreateTime() != null ? StringUtil.getFormatDate(doctor_list.get(i).getCreateTime()) : ""));
                sheet.addCell(new Label(j++, i + 1, doctor_list.get(i).getModifyTime() != null ? StringUtil.getFormatDate(doctor_list.get(i).getModifyTime()) : ""));
                sheet.addCell(new Label(j++, i + 1, doctor_list.get(i).getAuditReason()));
                System.out.println("introduce: " + doctor_list.get(i).getIntroduce());
                sheet.addCell(new Label(j++, i + 1, doctor_list.get(i).getIntroduce()));
            }
            //写入数据
            book.write();
            //关闭文件
            book.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
