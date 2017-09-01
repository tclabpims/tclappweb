package com.tcl.controller;

import com.tcl.model.UserModel;
import com.tcl.service.UserService;
import com.tcl.utils.StringUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuQi on 2017/8/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final int PAGE_SIZE = 8;

    @RequestMapping("/list")
    public String userList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Map<String, Object> result_map = getData(mapInfo);
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        map.put("list", result_map.get("list"));
        return "user/list";
    }

    @RequestMapping(value = "/query")
    public String queryPackageDetails(ModelMap map, UserModel userModel, String pageNo, String createTimeStart, String createTimeEnd) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("userName", userModel.getUserName().trim());
        mapInfo.put("name", userModel.getName().trim());
        mapInfo.put("status", userModel.getStatus().trim());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date create_time_start = null;
        Date create_time_end = null;
        try{
            create_time_start = dateFormat.parse(createTimeStart.trim());
            create_time_end = dateFormat.parse(createTimeEnd.trim());
        }catch (Exception e) {
            e.getStackTrace();
        }
        mapInfo.put("create_time_start", create_time_start);
        mapInfo.put("createTimeEnd", create_time_end);
        Map<String, Object> result_map =  getData(mapInfo);
        map.put("userName", userModel.getUserName().trim());
        map.put("name", userModel.getName().trim());
        map.put("status", userModel.getStatus().trim());
        map.put("createTimeStart", createTimeStart.trim());
        map.put("createTimeEnd", createTimeEnd.trim());
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        map.put("list", result_map.get("list"));
        map.put("query_flag", true);
        return "user/list";
    }

    /**
     * 增加用户
     * @param userModel
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUser(UserModel userModel, String birthStr) {
        userModel.setPassWord(StringUtil.MD5(userModel.getPassWord()));
        if(birthStr != "") {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthday = dateFormat.parse(birthStr.trim());
                userModel.setBirthday(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        userModel.setCreateTime(new Date());
        int result = userService.addUser(userModel);
        Map<String, String> map = new HashMap<String, String>();
        if(result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 通过id删除用户
     * @param id
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
            result = userService.deleteById(Long.parseLong(id));
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
     * 通过id查找用户
     * @param id
     * @return
     */
    @RequestMapping(value="/acquire", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, UserModel> selectById(String id) {
        //对接收到的字符串参数进行基本的处理与判断
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        Map<String, UserModel> map = new HashMap<String, UserModel>();
        if (matcher.matches()) {
            map.put("user", userService.selectById(Long.parseLong(id)));
        } else {
            UserModel userModel = new UserModel();
            userModel.setUserName("paramIsError");
            map.put("user", userModel);
        }
        return map;
    }

    /**
     * 更新用户信息
     * @param userModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateById(UserModel userModel) {
        userModel.setPassWord(StringUtil.MD5(userModel.getPassWord()));
        userModel.setModifyTime(new Date());
        int result = userService.updateById(userModel);
        Map<String, String> map = new HashMap<String, String>();
        if(result > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }

    private Map<String, Object> getData(Map<String, Object> mapInfo) {
        int page_no;
        boolean isEmpty = false;
        Map<String, Object> map = new HashMap<String, Object>();
        if (mapInfo.get("pageNo") == null || mapInfo.get("pageNo") == "") {
            page_no = 1;
        }
        else {
            page_no = Integer.parseInt(((String)mapInfo.get("pageNo")).trim());
            if (page_no < 1) {
                page_no = 1;
            }
        }
        List<UserModel> user_list = userService.selectList(mapInfo);
        int total_page = (user_list.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        if (total_page < 1) {
            total_page = 1;
            isEmpty = true;
        }
        if(page_no > total_page) {
            page_no = total_page;
        }
        map.put("pageNo", page_no);
        map.put("totalPage", total_page);
        if(!isEmpty) {
            mapInfo.put("start_num", (page_no - 1) * PAGE_SIZE);
            mapInfo.put("pageSize", PAGE_SIZE);
            List<UserModel> userModels = userService.selectList(mapInfo);
            map.put("list", userModels);
        }else {
            map.put("list",new ArrayList<UserModel>());
        }
        return map;
    }
    /**
     * 导出Excel功能
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportexcel", method = RequestMethod.POST)
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserModel> user_list = null;
        //标题行
        String title[] = {"用户Id", "用户名", "姓名", "性别", "状态", "身份证号", "生日", "地址", "验证码", "验证码发送时间", "创建时间", "修改时间"};
        StringBuilder tempPath = new StringBuilder();
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddkkmmss_S");
        tempPath.append(fileNameFormat.format(new Date()));
        tempPath.append(".").append("xls");
        String filename = tempPath.toString();
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//			response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename).getBytes(), "iso-8859-1"));
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((filename).getBytes(), "utf-8"));
            OutputStream os = response.getOutputStream();
            WritableWorkbook book = Workbook.createWorkbook(os);
            WritableSheet sheet = book.createSheet("套餐信息", 0);
            //写入标题
            for (int i=0; i<title.length; i++) {
                sheet.addCell(new Label(i, 0, title[i]));
            }
            user_list = userService.selectList(new HashMap<String, Object>());
            for (int i=0; i<user_list.size(); i++) {
                int j=0;
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getId() != null ? Long.toString(user_list.get(i).getId()) : ""));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getUserName()));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getName()));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getSex()));
                if (user_list.get(i).getStatus().equals("1")) {
                    sheet.addCell(new Label(j++, i + 1, "可用"));
                } else if (user_list.get(i).getStatus().equals("2")) {
                    sheet.addCell(new Label(j++, i + 1, "不可用"));
                } else {
                    sheet.addCell(new Label(j++, i + 1, "暂无"));
                }
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getSfzNum()));
                sheet.addCell(new Label(j++, i + 1, user_list.get(i).getBirthday() != null ? StringUtil.getFormatDate(user_list.get(i).getBirthday(), "yyyy-MM-dd") : ""));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getAddress()));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getVerificationCode()));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getCodeSendTime() != null ? StringUtil.getFormatDate(user_list.get(i).getCodeSendTime()) : ""));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getCreateTime() != null ? StringUtil.getFormatDate(user_list.get(i).getCreateTime()) : ""));
                sheet.addCell(new Label(j++, i+1, user_list.get(i).getModifyTime() != null ? StringUtil.getFormatDate(user_list.get(i).getModifyTime()) : ""));
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

    /**
     * excel数据的导入
     * @param excelFile
     * @return
     */
    @RequestMapping(value = "/excelImport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String , String> importExcelFile(MultipartFile excelFile) {
        Map<String, String> map = new HashMap<String, String>();
        String result = userService.importExcelFile(excelFile);
        map.put("msg", result);
        return map;
    }
}
