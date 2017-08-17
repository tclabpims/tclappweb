package com.tcl.service.impl;

import com.tcl.dao.UserModelMapper;
import com.tcl.model.UserModel;
import com.tcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
