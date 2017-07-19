package com.tcl.service;

import com.tcl.model.DoctorModel;

import java.util.List;

/**
 * Created by LiuQi on 2017/7/6.
 */
public interface DoctorService {

    /**
     * ͨ��ҽ��ID��ҽ��
     * @param id
     * @return
     */
    DoctorModel selectById(Long id);

    /**
     * �г�ĳ�����͵�ҽ��
     * @param type
     * @return
     */
    List<DoctorModel> selectByType(String type);

    /**
     * ͨ��Id��ĳλҽ�������ݿ���ɾ��
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * ͨ��Id�������ݿ�
     * @param record
     * @return
     */
    int updateById(DoctorModel record);
}
