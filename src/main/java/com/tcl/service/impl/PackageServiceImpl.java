package com.tcl.service.impl;

import com.tcl.dao.PackageModelMapper;
import com.tcl.model.PackageModel;
import com.tcl.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageModelMapper packageDao;

	public PackageModel selectById(Long id) {
		return packageDao.selectByPrimaryKey(id);
	}

	public List<PackageModel> selectList(Map map) {
		return packageDao.selectList(map);
	}
}
