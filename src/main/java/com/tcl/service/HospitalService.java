package com.tcl.service;

import com.tcl.model.HospitalModel;
import com.tcl.model.HospitalModelWithBLOBs;

import java.util.List;

/**
 * Created by wang on 2017-07-03.
 */
public interface HospitalService {
    HospitalModelWithBLOBs selectById(Long id);

    List<HospitalModel> selectByType(String type);

    List<HospitalModel> selectByPageInfo(String type, int page_no, int pageSize);

    int addHospital(HospitalModelWithBLOBs hospitalModel);

    List<HospitalModel> queryByPageInfo(String name, String telphone, int page_no, int pageSize);

    List<HospitalModel> queryByInfo(String name, String telphone);

    int deleteById(Long id);

    int updateById(HospitalModelWithBLOBs hospitalModel);
}
