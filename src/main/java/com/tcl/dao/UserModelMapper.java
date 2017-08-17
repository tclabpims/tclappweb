package com.tcl.dao;

import com.tcl.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);

    List<UserModel> selectList(Map<String, Object> mapInfo);
}