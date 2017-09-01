package com.tcl.service;

import com.tcl.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/29.
 */
public interface FinaManageService {

    List<OrderModelWithBLOBs> orderList(Map<String, Object> map);

    List<HospitalModelWithBLOBs> hospitalList(Map<String, Object> map);

    List<PackageModelWithBLOBs> packageList(Map<String, Object> map);
}
