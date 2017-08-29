package com.tcl.service.impl;

import com.tcl.dao.OrderModelMapper;
import com.tcl.dao.TradeModelMapper;
import com.tcl.model.OrderModelWithBLOBs;
import com.tcl.model.TradeModel;
import com.tcl.service.OrderService;
import com.tcl.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderModelMapper orderDao;

    public List<OrderModelWithBLOBs> selectList(Map map) {
        return orderDao.selectList(map);
    }

    public OrderModelWithBLOBs selectById(long id) {
        return orderDao.selectByPrimaryKey(id);
    }

    public int updateById(OrderModelWithBLOBs orderModel) {
        return orderDao.updateByPrimaryKeySelective(orderModel);
    }

    public int deleteById(long id) {
        return orderDao.deleteByPrimaryKey(id);
    }

    /*public int addPackageDetails(PackageDetailsModel packageDetailsModel) {
        return packageDetailsDao.insertSelective(packageDetailsModel);
    }*/

    /*public String importExcelFile(MultipartFile excelFile) {
        String result = "";
        //创建处理Excel的类
        ReadExcel readExcel = new ReadPackageDetailsExcel();
        List<PackageDetailsModel> userLists = readExcel.getExcelInfo(excelFile);
        if (userLists != null && !userLists.isEmpty()) {
            //userDao.batchInsert(userLists);
            for (int i=0; i<userLists.size(); i++) {
                packageDetailsDao.insert(userLists.get(i));
            }
            result = "导入成功";
        } else {
            result = "导入失败";
        }
        return result;
    }*/
}
