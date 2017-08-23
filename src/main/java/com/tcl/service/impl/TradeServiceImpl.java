package com.tcl.service.impl;

import com.tcl.dao.CartModelMapper;
import com.tcl.dao.TradeModelMapper;
import com.tcl.model.CartModel;
import com.tcl.model.TradeModel;
import com.tcl.service.CartService;
import com.tcl.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Service
public class TradeServiceImpl implements TradeService{

    @Autowired
    private TradeModelMapper tradeDao;

    public List<TradeModel> selectList(Map map) {
        return tradeDao.selectList(map);
    }

    public TradeModel selectById(long id) {
        return tradeDao.selectByPrimaryKey(id);
    }

    public int updateById(TradeModel tradeModel) {
        return tradeDao.updateByPrimaryKeySelective(tradeModel);
    }

    public int deleteById(long id) {
        return tradeDao.deleteByPrimaryKey(id);
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
