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

	/**
	 * 列出每页的广告
	 * @param type
	 * @param page_no
	 * @param pageSize
	 * @return
	 */
	public List<NewspaperModel> selectByPageInfo(String type, int page_no, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		int start_num = (page_no - 1) * pageSize;
		int end_num = page_no * pageSize;
		map.put("start_num", start_num);
		map.put("end_num", end_num);
		return newspaperDao.selectByPageInfo(map);
	}

	/**
	 * 通过参数查询所有广告
	 * @param newspaperModel
	 * @return
	 */
	public List<NewspaperModel> queryByInfo(NewspaperModel newspaperModel) {
		return newspaperDao.queryByInfo(newspaperModel);
	}

	/**
	 * 通过参数查询每页的广告
	 * @param title
	 * @param type
	 * @param status
	 * @param page_no
	 * @param pageSize
	 * @return
	 */
	public List<NewspaperModel> queryByPageInfo(String title, String type, String status, int page_no, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("type", type);
		map.put("status", status);
		map.put("pageSize", pageSize);
		int start_num = (page_no - 1) * pageSize;
		int end_num = page_no * pageSize;
		map.put("start_num", start_num);
		map.put("end_num", end_num);
		return newspaperDao.queryByPageInfo(map);
	}

	/**
	 * 增加新闻
	 * @param newspaperModel
	 * @return
	 */
	public int addNewspaper(NewspaperModel newspaperModel) {
		return newspaperDao.insertSelective(newspaperModel);
	}

	/**
	 * 通过ID删除某条记录
	 * @param id
	 * @return
	 */
	public int deleteById(long id) {
		return newspaperDao.deleteByPrimaryKey(id);
	}

	/**
	 * 更新某条记录的信息
	 * @param hospitalModel
	 * @return
	 */
	public int updateById(NewspaperModel hospitalModel) {
		return newspaperDao.updateByPrimaryKeySelective(hospitalModel);
	}
}
