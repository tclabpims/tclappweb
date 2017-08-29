package com.tcl.service;

import com.tcl.model.OrderModelWithBLOBs;
import com.tcl.model.TradeModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
public interface OrderService {

    List<OrderModelWithBLOBs> selectList(Map map);


    OrderModelWithBLOBs selectById(long id);

    int updateById(OrderModelWithBLOBs orderModel);

    int deleteById(long id);

//    int addCart(CartModel cartModel);

//    String importExcelFile(MultipartFile excelFile);
}
