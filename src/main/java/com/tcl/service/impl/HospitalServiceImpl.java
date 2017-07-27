package com.tcl.service.impl;

import com.tcl.dao.HospitalModelMapper;
import com.tcl.model.HospitalModel;
import com.tcl.model.HospitalModelWithBLOBs;
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

	public HospitalModelWithBLOBs selectById(Long id) {
		return hospitalDao.selectByPrimaryKey(id);
	}

	public List<HospitalModel> selectByType(String type) {
		Map map=new HashMap<String,String>();
		map.put("type",type);
		return hospitalDao.selectByType(map);
	}

	public List<HospitalModel> selectByPageInfo(String type, int page_no, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		int start_num = (page_no - 1) * pageSize;
		int end_num = page_no * pageSize;
		map.put("start_num", start_num);
		map.put("end_num", end_num);
		return hospitalDao.selectByPageInfo(map);
	}

	public int addHospital(HospitalModelWithBLOBs hospitalModel) {
		return hospitalDao.insert(hospitalModel);
	}

	/**
	 * 通过参数查询一页医生
	 * @param name
	 * @param telphone
	 * @param page_no
	 * @param pageSize
	 * @return
	 */
	public List<HospitalModel> queryByPageInfo(String name, String telphone, int page_no, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("telphone", telphone);
		int start_num = (page_no - 1) * pageSize;
		int end_num = page_no * pageSize;
		map.put("start_num", start_num);
		map.put("end_num", end_num);
		return hospitalDao.queryByPageInfo(map);
	}

	/**
	 * 通过参数胡查询医生
	 * @param name
	 * @param telphone
	 * @return
	 */
	public List<HospitalModel> queryByInfo(String name, String telphone) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("telphone", telphone);
		return hospitalDao.queryByInfo(map);
	}

	/**
	 * 通过ID删除某位医生
	 * @param id
	 * @return
	 */
	public int deleteById(Long id) {
		return hospitalDao.deleteByPrimaryKey(id);
	}
}
