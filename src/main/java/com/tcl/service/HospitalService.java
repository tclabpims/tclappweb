package com.tcl.service;

import com.tcl.model.HospitalModel;

import java.util.List;

/**
 * Created by wang on 2017-07-03.
 */
public interface HospitalService {

    HospitalModel selectById(Long id);

    List<HospitalModel> selectByType(String type);
}
