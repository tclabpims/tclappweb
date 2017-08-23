package com.tcl.service;

import com.tcl.model.CartModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
public interface CartService {

    List<CartModel> selectList(Map map);

//    int addCart(CartModel cartModel);

    CartModel selectById(long id);

    int updateById(CartModel cartModel);

    int deleteById(long id);

//    String importExcelFile(MultipartFile excelFile);
}
