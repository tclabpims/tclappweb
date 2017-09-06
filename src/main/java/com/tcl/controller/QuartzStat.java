package com.tcl.controller;

import com.tcl.service.HospitalSaleStatService;
import com.tcl.service.PackageSaleStatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiuQi on 2017/9/3.
 */
public class QuartzStat {

    private ExecutorService singleThreadExecutor = null;

    @Autowired
    private HospitalSaleStatService hospitalSaleStatService;

    @Autowired
    private PackageSaleStatService packageSaleStatService;

    public void work() {
        if (singleThreadExecutor == null) {
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        } else {
            singleThreadExecutor.shutdown();
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        try {
            saveInfo();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void saveInfo() {
        //统计销售数据，并将它们存入数据库
        hospitalSaleStatService.statHospitalSaleData();
        packageSaleStatService.statPackageSaleData();
    }
}
