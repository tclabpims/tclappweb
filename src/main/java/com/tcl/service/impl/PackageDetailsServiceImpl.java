package com.tcl.service.impl;

import com.tcl.dao.PackageDetailsModelMapper;
import com.tcl.model.PackageDetailsModel;
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
public class PackageDetailsServiceImpl implements PackageDetailsService{

    @Autowired
    private PackageDetailsModelMapper packageDetailsDao;

    public List<PackageDetailsModel> selectList(Map map) {
        return packageDetailsDao.selectList(map);
    }

    public int addPackageDetails(PackageDetailsModel packageDetailsModel) {
        return packageDetailsDao.insertSelective(packageDetailsModel);
    }

    public PackageDetailsModel selectById(long id) {
        return packageDetailsDao.selectByPrimaryKey(id);
    }

    public int updateById(PackageDetailsModel packageDetailsModel) {
        return packageDetailsDao.updateByPrimaryKeySelective(packageDetailsModel);
    }

    public int deleteById(long id) {
        return packageDetailsDao.deleteByPrimaryKey(id);
    }

    public String importExcelFile(MultipartFile excelFile) {
        String result = "";
        //创建处理Excel的类
        ReadExcel readExcel = new ReadPackageDetailsExcel();
        List<PackageDetailsModel> packageDetailsLists = readExcel.getExcelInfo(excelFile);
        if (packageDetailsLists != null && !packageDetailsLists.isEmpty()) {
            packageDetailsDao.batchInsert(packageDetailsLists);
            /*for (int i=0; i<userLists.size(); i++) {
                packageDetailsDao.insert(userLists.get(i));
            }*/
            result = "导入成功";
        } else {
            result = "导入失败";
        }
        return result;
    }
}
