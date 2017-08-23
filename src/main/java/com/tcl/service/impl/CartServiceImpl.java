package com.tcl.service.impl;

import com.tcl.dao.CartModelMapper;
import com.tcl.dao.PackageDetailsModelMapper;
import com.tcl.model.CartModel;
import com.tcl.model.PackageDetailsModel;
import com.tcl.service.CartService;
import com.tcl.service.PackageDetailsService;
import com.tcl.utils.excel.ReadExcel;
import com.tcl.utils.excel.impl.ReadPackageDetailsExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartModelMapper cartDao;

    public List<CartModel> selectList(Map map) {
        return cartDao.selectList(map);
    }

    /*public int addPackageDetails(PackageDetailsModel packageDetailsModel) {
        return packageDetailsDao.insertSelective(packageDetailsModel);
    }*/

    public CartModel selectById(long id) {
        return cartDao.selectByPrimaryKey(id);
    }

    public int updateById(CartModel cartModel) {
        return cartDao.updateByPrimaryKeySelective(cartModel);
    }

    public int deleteById(long id) {
        return cartDao.deleteByPrimaryKey(id);
    }

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
