package com.tcl.controller;

import com.tcl.model.CartModel;
import com.tcl.model.OrderModel;
import com.tcl.model.OrderModelWithBLOBs;
import com.tcl.model.TradeModel;
import com.tcl.service.OrderService;
import com.tcl.service.TradeService;
import com.tcl.utils.ExcelExportUtil;
import com.tcl.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.ext.GBK;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final int PAGE_SIZE = 8;

    /**
     * 列出所有购物车数据
     * @param map
     * @param pageNo
     * @return
     */
    @RequestMapping("/list")
    public String orderList(ModelMap map, String pageNo) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        Map<String, Object> result_map = getData(mapInfo);
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        map.put("list", result_map.get("list"));
        return "order/list";
    }

    /**
     * 通过参数查询
     * @param map
     * @param userName
     * @param tradeNum
     * @param packageName
     * @param status
     * @param pageNo
     * @param barcode
     * @param takeTimeStart
     * @param takeTimeEnd
     * @return
     */
    @RequestMapping(value = "/query")
    public String queryPackageDetails(ModelMap map, String userName, String tradeNum, String packageName, String status,
                                      String pageNo, String barcode, String hospitalName, String takeTimeStart, String takeTimeEnd) {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        mapInfo.put("pageNo", pageNo);
        mapInfo.put("userName", userName.trim());
        mapInfo.put("tradeNum", tradeNum.trim());
        mapInfo.put("packageName", packageName.trim());
        mapInfo.put("hospitalName", hospitalName.trim());
        Integer status_ = null;
        if (status != null && status != "") {
            status_ = Integer.parseInt(status.trim());
        }
        mapInfo.put("status", status_);
        mapInfo.put("barcode", barcode.trim());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date take_time_start = null;
        Date take_time_end = null;
        try{
            take_time_start = dateFormat.parse(takeTimeStart.trim());
            take_time_end = dateFormat.parse(takeTimeEnd.trim());
        }catch (Exception e) {
            e.getStackTrace();
        }
        mapInfo.put("take_time_start", take_time_start);
        mapInfo.put("take_time_end", take_time_end);
        Map<String, Object> result_map =  getData(mapInfo);
        map.put("userName_", userName.trim());
        map.put("tradeNum", tradeNum.trim());
        map.put("packageName", packageName.trim());
        map.put("status", status.trim());
        map.put("barcode", barcode.trim());
        map.put("takeTimeStart", takeTimeStart.trim());
        map.put("takeTimeEnd", takeTimeEnd.trim());
        map.put("hospitalName", hospitalName.trim());
        map.put("pageNo", result_map.get("pageNo"));
        map.put("totalPage", result_map.get("totalPage"));
        map.put("list", result_map.get("list"));
        map.put("query_flag", true);
        return "order/list";
    }

    /**
     * 通过ID查找订单
     * @param id
     * @return*/
    @RequestMapping(value = "/acquire", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectById(String id) {
        //对接收到的字符串参数进行基本的处理与判断
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (matcher.matches()) {
            map.put("order", orderService.selectById(Long.parseLong(id)));
        } else {
            OrderModelWithBLOBs orderModel = new OrderModelWithBLOBs();
            orderModel.setPackageName("paramIsError");
            map.put("order", orderModel);
        }
        return map;
    }

    /**
     * 通过ID查找订单子项以及它的关联信息
     * @param id
     * @return*/
    @RequestMapping(value = "/acquireTrade", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> selectRelatedInfoById(String id) {
        //对接收到的字符串参数进行基本的处理与判断
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (matcher.matches()) {
            Map<String, Object> mapInfo = new HashMap<String, Object>();
            mapInfo.put("id", Long.parseLong(id));
            map.put("order", orderService.selectList(mapInfo).get(0));
        } else {
            OrderModelWithBLOBs orderModel = new OrderModelWithBLOBs();
            orderModel.setPackageName("paramIsError");
            map.put("order", orderModel);
        }
        return map;
    }

    /**
     * 更新订单内容
     * @param orderModelWithBLOBs
     * @return*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateById(OrderModelWithBLOBs orderModelWithBLOBs, String takeTimeS, String unscrambleTimeS, String price_) {
        orderModelWithBLOBs.setPrice(StringUtil.priceProcess(price_));
        orderModelWithBLOBs.setTakeTime(StringUtil.getDate(takeTimeS, "yyyy-MM-dd hh:mm:ss"));
        orderModelWithBLOBs.setUnscrambleTime(StringUtil.getDate(unscrambleTimeS, "yyyy-MM-dd hh:mm:ss"));
        orderModelWithBLOBs.setModifyTime(new Date());
        int result = orderService.updateById(orderModelWithBLOBs);
        Map<String, String> map = new HashMap<String, String>();
        if (result > 0) {
            map.put("msg", "success");
        }else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 通过ID删除某条记录
     * @param id
     * @return*/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteById(String id) {
        //对接收到的字符串参数进行基本的处理与判断
        id = id.trim();
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(id);
        int result;
        Map<String, String> map = new HashMap<String, String>();
        if(matcher.matches()) {
            result = orderService.deleteById(Long.parseLong(id));
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
        List<OrderModelWithBLOBs> orderList = orderService.selectList(mapInfo);
        int total_page = (orderList.size() + PAGE_SIZE - 1) / PAGE_SIZE;
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
            List<OrderModelWithBLOBs> orders = orderService.selectList(mapInfo);
            map.put("list", orders);
        }else {
            map.put("list",new ArrayList<OrderModel>());
        }
        return map;
    }

    /**
     * 导出Excel功能
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportexcel", method = RequestMethod.POST)
    public String exportExcel(HttpServletResponse response, String userName, String tradeNum,
                              String packageName, String status, String barcode, String hospitalName,
                              String takeTimeStart, String takeTimeEnd) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName.trim());
        map.put("tradeNum", tradeNum.trim());
        map.put("packageName", packageName.trim());
        Integer status_ = null;
        if (status != null && status != "") {
            status_ = Integer.parseInt(status.trim());
        }
        map.put("status", status_);
        map.put("barcode", barcode.trim());
        map.put("hospitalName", hospitalName.trim());
        map.put("take_time_start",StringUtil.getDate(takeTimeStart, "yyyy-MM-dd HH:mm:ss"));
        map.put("take_time_end", StringUtil.getDate(takeTimeEnd, "yyyy-MM-dd HH:mm:ss"));
        List<OrderModelWithBLOBs> orderList = orderService.selectOrdersForExcelExport(map);
        ExcelExportUtil.orderRecoredExport(response, orderList);
//        System.out.println("你好，我是中国人");
        return null;
    }

    /**
     * excel数据的导入
     * @param excelFile
     * @return
     */
    /*@RequestMapping(value = "/excelImport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String , String> importExcelFile(MultipartFile excelFile) {
        Map<String, String> map = new HashMap<String, String>();
        String result = packageDetailsService.importExcelFile(excelFile);
        map.put("msg", result);
        return map;
    }*/
}
