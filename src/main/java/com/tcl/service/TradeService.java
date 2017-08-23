package com.tcl.service;

import com.tcl.model.CartModel;
import com.tcl.model.TradeModel;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
public interface TradeService {

    List<TradeModel> selectList(Map map);


    TradeModel selectById(long id);

    int updateById(TradeModel tradeModel);

    int deleteById(long id);

//    int addCart(CartModel cartModel);

//    String importExcelFile(MultipartFile excelFile);
}
