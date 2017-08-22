package com.tcl.service;

import com.tcl.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/16.
 */
public interface UserService {

    List<UserModel> selectList(Map<String, Object> mapInfo);

    int addUser(UserModel userModel);

    int deleteById(long id);

    UserModel selectById(long id);

    int updateById(UserModel userModel);

    String importExcelFile(MultipartFile excelFile);
}
