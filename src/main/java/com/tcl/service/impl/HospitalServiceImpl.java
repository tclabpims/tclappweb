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

	public List<HospitalModelWithBLOBs> selectByType(String type) {
		Map map=new HashMap<String,String>();
		map.put("type",type);
		return hospitalDao.selectByType(map);
	}

	public List<HospitalModel> selectByPageInfo(String type, int page_no, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		int start_num = (page_no - 1) * pageSize;
		map.put("start_num", start_num);
		map.put("pageSize", pageSize);
		return hospitalDao.selectByPageInfo(map);
	}

	/**
	 * 增加医院
	 * @param hospitalModel
	 * @return
	 */
	public int addHospital(HospitalModelWithBLOBs hospitalModel) {
		return hospitalDao.insertSelective(hospitalModel);
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
		map.put("start_num", start_num);
		map.put("pageSize", pageSize);
		return hospitalDao.queryByPageInfo(map);
	}

	/**
	 * 通过参数查询医生
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
	 * 通过ID删除个医院
	 * @param id
	 * @return
	 */
	public int deleteById(Long id) {
		return hospitalDao.deleteByPrimaryKey(id);
	}

	/**
	 * 通过ID更新医院信息
	 * @param hospitalModel
	 * @return
	 */
	public int updateById(HospitalModelWithBLOBs hospitalModel) {
		return hospitalDao.updateByPrimaryKeySelective(hospitalModel);
	}
}
