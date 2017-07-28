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
     * 列出当前页需要显示的医生
     * @param type
     * @param page_no
     * @param page_size
     * @return
     */
    public List<DoctorModel> selectByPage(String type, int page_no, int page_size) {
        Map map = new HashMap<String, String>();
        int start_num = (page_no - 1) * page_size;
        int end_num = page_no * page_size;
        map.put("type",type);
        map.put("start_num", start_num);
        map.put("end_num", end_num);
        return doctorDao.selectByPage(map);
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
        return doctorDao.updateByPrimaryKeySelective(record);
    }

    /**
     * 增加一位医生
     * @param doctorModel
     * @return
     */
    public int addADoctor(DoctorModel doctorModel) {
        return doctorDao.insertSelective(doctorModel);
    }

    /**
     * 通过参数信息查询医生
     * @param userName
     * @param hospitalId
     * @param doctorName
     * @param title
     * @param status
     * @param type
     * @param create_time_start
     * @param create_time_end
     * @return
     */
    public List<DoctorModel> queryByInfo(String userName, Long hospitalId, String doctorName, String title, String status,
                                         String type, Date create_time_start, Date create_time_end) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("hospitalId", hospitalId);
        map.put("doctorName", doctorName);
        map.put("title", title);
        map.put("status", status);
        map.put("type", type);
        map.put("create_time_start", create_time_start);
        map.put("create_time_end", create_time_end);
        return doctorDao.queryDoctorByInfo(map);
    }

    /**
     * 通过参数信息查询一页医生
     * @param userName
     * @param hospitalId
     * @param doctorName
     * @param title
     * @param status
     * @param type
     * @param create_time_start
     * @param create_time_end
     * @param page_no
     * @param page_size
     * @return
     */
    public List<DoctorModel> queryByInfoPgae(String userName, Long hospitalId, String doctorName, String title,
                                             String status, String type, Date create_time_start, Date create_time_end,
                                             int page_no, int page_size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("hospitalId", hospitalId);
        map.put("doctorName", doctorName);
        map.put("title", title);
        map.put("status", status);
        map.put("type", type);
        map.put("create_time_start", create_time_start);
        map.put("create_time_end", create_time_end);
        int start_num = (page_no - 1) * page_size;
        int end_num = page_no * page_size;
        map.put("start_num", start_num);
        map.put("end_num", end_num);
        return doctorDao.queryPageDoctorByInfo(map);
    }
}
