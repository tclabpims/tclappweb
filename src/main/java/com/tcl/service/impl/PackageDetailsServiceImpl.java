package com.tcl.service.impl;

import com.tcl.dao.PackageDetailsModelMapper;
import com.tcl.model.PackageDetailsModel;
import com.tcl.service.PackageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Service
public class PackageDetailsServiceImpl implements PackageDetailsService{

    @Autowired
    private PackageDetailsModelMapper packageDetailsDao;

    public List<PackageDetailsModel> selectList(Map map) {
        return packageDetailsDao.selectList(map);
    }

    public int addPackageDetails(PackageDetailsModel packageDetailsModel) {
        return packageDetailsDao.insertSelective(packageDetailsModel);
    }

    public PackageDetailsModel selectById(long id) {
        return packageDetailsDao.selectByPrimaryKey(id);
    }

    public int updateById(PackageDetailsModel packageDetailsModel) {
        return packageDetailsDao.updateByPrimaryKeySelective(packageDetailsModel);
    }

    public int deleteById(long id) {
        return packageDetailsDao.deleteByPrimaryKey(id);
    }
}
