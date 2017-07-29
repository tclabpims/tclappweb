package com.tcl.service.impl;

import com.tcl.dao.NewspaperModelMapper;
import com.tcl.model.NewspaperModel;
import com.tcl.service.NewspaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
	public class NewspaperServiceImpl implements NewspaperService {

		@Autowired
		private NewspaperModelMapper newspaperDao;


	public NewspaperModel selectById(Long id) {
		return newspaperDao.selectByPrimaryKey(id);
	}

	public List<NewspaperModel> selectByType(String type) {
		Map map=new HashMap<String,String>();
		map.put("type",type);
		return newspaperDao.selectByType(map);
	}
}
