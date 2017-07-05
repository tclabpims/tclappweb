package com.tcl.dao;

import com.tcl.model.ApplicantModel;

public interface ApplicantModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApplicantModel record);

    int insertSelective(ApplicantModel record);

    ApplicantModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApplicantModel record);

    int updateByPrimaryKey(ApplicantModel record);
}