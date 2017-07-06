package com.tcl.service.impl;

import com.tcl.dao.HospitalModelMapper;
import com.tcl.model.HospitalModel;
import com.tcl.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalModelMapper hospitalDao;

	public HospitalModel selectById(Long id) {
		return hospitalDao.selectByPrimaryKey(id);
	}

	public List<HospitalModel> selectByType(String type) {
		Map map=new HashMap<String,String>();
		map.put("type",type);
		return hospitalDao.selectByType(map);
	}
}
