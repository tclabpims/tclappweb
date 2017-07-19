package com.tcl.service.impl;


import com.tcl.dao.DoctorModelMapper;
import com.tcl.model.DoctorModel;
import com.tcl.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by LiuQi on 2017/7/7.
 */
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    DoctorModelMapper doctorDao;

    /**
     * 通过医生ID找医生
     * @param id
     * @return
     */
    public DoctorModel selectById(Long id) {
        return doctorDao.selectByPrimaryKey(id);
    }

    /**
     * 列出某种类型的医生
     * @param type
     * @return
     */
    public List<DoctorModel> selectByType(String type) {
        Map map = new HashMap<String, String>();
        map.put("type",type);
        return doctorDao.selectByType(map);
    }

    /**
     * 通过Id将某位医生从数据库中删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
        return doctorDao.deleteByPrimaryKey(id);
    }

    /**
     * 通过Id更新数据库
     * @param record
     * @return
     */
    public int updateById(DoctorModel record) {
        return doctorDao.updateByPrimaryKey(record);
    }
}
