package com.tcl.service.impl;

import com.tcl.dao.PackageModelMapper;
import com.tcl.model.PackageModel;
import com.tcl.model.PackageModelWithBLOBs;
import com.tcl.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageModelMapper packageDao;

	public PackageModelWithBLOBs selectById(Long id) {
		return packageDao.selectByPrimaryKey(id);
	}

	public List<PackageModel> selectList(Map map) {
		return packageDao.selectList(map);
	}

	public List<PackageModel> selectListByPage(Map map) {
		return packageDao.selectListByPage(map);
	}

	public List<PackageModel> queryPackage(Map map) {
		return packageDao.queryPackage(map);
	}

	public List<PackageModel> queryListByPage(Map map) {
		return packageDao.queryListByPage(map);
	}

	public int deleteById(long id) {
		return packageDao.deleteByPrimaryKey(id);
	}

	public int addPackage(PackageModelWithBLOBs packageModel) {
		return packageDao.insertSelective(packageModel);
	}

	public int updateById(PackageModelWithBLOBs packageMode) {
		return packageDao.updateByPrimaryKeySelective(packageMode);
	}
}
