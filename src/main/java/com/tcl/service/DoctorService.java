package com.tcl.service;

import com.tcl.model.DoctorModel;

import java.util.Date;
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
     * 列出当前页需要显示的医生
     * @param type
     * @param page_no
     * @param page_size
     * @return
     */
    List<DoctorModel> selectByPage(String type, int page_no, int page_size);

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

    /**
     * 增加一位医生
     * @param doctorModel
     * @return
     */
    int addADoctor(DoctorModel doctorModel);

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
    List<DoctorModel> queryByInfo(String userName, Long hospitalId, String doctorName, String title,
                                  String status, String type, Date create_time_start, Date create_time_end);

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
     * @return
     */
    List<DoctorModel> queryByInfoPgae(String userName, Long hospitalId, String doctorName, String title,
                                  String status, String type, Date create_time_start, Date create_time_end, int page_no, int page_size);
}
