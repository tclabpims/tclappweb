package com.tcl.service;

import com.tcl.model.DoctorModel;

import java.util.List;

/**
 * Created by LiuQi on 2017/7/6.
 */
public interface DoctorService {

    /**
     * 通过医生ID找医生
     * @param id
     * @return
     */
    DoctorModel selectById(Long id);

    /**
     * 列出某种类型的医生
     * @param type
     * @return
     */
    List<DoctorModel> selectByType(String type);

    /**
     * 通过Id将某位医生从数据库中删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 通过Id更新数据库
     * @param record
     * @return
     */
    int updateById(DoctorModel record);
}
