package com.tcl.service.impl;

import com.tcl.dao.UserModelMapper;
import com.tcl.model.UserModel;
import com.tcl.service.UserService;
import com.tcl.utils.excel.ReadExcel;
import com.tcl.utils.excel.impl.ReadUserExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserModelMapper userDao;

    public List<UserModel> selectList(Map<String, Object> mapInfo) {
        return userDao.selectList(mapInfo);
    }

    public int addUser(UserModel userModel) {
        return userDao.insertSelective(userModel);
    }

    public int deleteById(long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public UserModel selectById(long id) {
        return userDao.selectByPrimaryKey(id);
    }

    public int updateById(UserModel userModel) {
        return userDao.updateByPrimaryKeySelective(userModel);
    }

    public String importExcelFile(MultipartFile excelFile) {
        String result = "";
        //创建处理Excel的类
        ReadExcel readExcel = new ReadUserExcel();
        List<UserModel> userLists = readExcel.getExcelInfo(excelFile);
        if (userLists != null && !userLists.isEmpty()) {
            userDao.batchInsert(userLists);
            /*for (int i=0; i<userLists.size(); i++) {
                userDao.insert(userLists.get(i));
            }*/
            result = "导入成功";
        } else {
            result = "导入失败";
        }
        return result;
    }
}
