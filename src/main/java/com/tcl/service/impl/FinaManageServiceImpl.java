package com.tcl.service.impl;

import com.tcl.dao.HospitalModelMapper;
import com.tcl.dao.OrderModelMapper;
import com.tcl.dao.PackageModelMapper;
import com.tcl.model.*;
import com.tcl.service.FinaManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/29.
 */
@Service
public class FinaManageServiceImpl implements FinaManageService {

    @Autowired
    private OrderModelMapper orderDao;

    @Autowired
    private HospitalModelMapper hospitalDao;

    @Autowired
    private PackageModelMapper packageDao;

    public List<OrderModelWithBLOBs> orderList(Map<String, Object> map) {
        return orderDao.orderWithHospitalInfoList(map);
    }

    public List<HospitalModelWithBLOBs> hospitalList(Map<String, Object> map) {
        return hospitalDao.selectByType(map);
    }

    public List<PackageModelWithBLOBs> packageList(Map<String, Object> map) {
        return packageDao.selectList(map);
    }
}
