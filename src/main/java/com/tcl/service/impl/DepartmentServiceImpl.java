package com.tcl.service.impl;

import com.tcl.dao.DepartmentModelMapper;
import com.tcl.dao.PackageDetailsModelMapper;
import com.tcl.model.DepartmentModel;
import com.tcl.model.PackageDetailsModel;
import com.tcl.service.DepartmentService;
import com.tcl.service.PackageDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuQi on 2017/8/15.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentModelMapper departmentDao;

    public List<DepartmentModel> selectList(Map map) {
        return departmentDao.selectList(map);
    }

    public int addDepartment(DepartmentModel departmentModel) {
        return departmentDao.insertSelective(departmentModel);
    }

    public DepartmentModel selectById(long id) {
        return departmentDao.selectByPrimaryKey(id);
    }

    public int updateById(DepartmentModel departmentModel) {
        return departmentDao.updateByPrimaryKeySelective(departmentModel);
    }

    public int deleteById(long id) {
        return departmentDao.deleteByPrimaryKey(id);
    }
}
