package com.tcl.service;

import com.tcl.model.PackageDetailsModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
public interface PackageDetailsService {

    List<PackageDetailsModel> selectList(Map map);

    int addPackageDetails(PackageDetailsModel packageDetailsModel);

    PackageDetailsModel selectById(long id);

    int updateById(PackageDetailsModel packageDetailsModel);

    int deleteById(long id);

    String importExcelFile(MultipartFile excelFile);
}
