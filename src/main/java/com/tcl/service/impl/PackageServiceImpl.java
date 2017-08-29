package com.tcl.service.impl;

import com.tcl.dao.PackageModelMapper;
import com.tcl.model.PackageModel;
import com.tcl.model.PackageModelWithBLOBs;
import com.tcl.service.PackageService;
import com.tcl.utils.excel.ReadExcel;
import com.tcl.utils.excel.impl.ReadPackageExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageModelMapper packageDao;

	public PackageModelWithBLOBs selectById(Long id) {
		return packageDao.selectByPrimaryKey(id);
	}

	public List<PackageModelWithBLOBs> selectList(Map map) {
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

	public String importExcelFile(MultipartFile excelFile) {
		String result = "";
		//创建处理Excel的类
		ReadExcel readExcel = new ReadPackageExcel();
		List<PackageModelWithBLOBs> packageList = readExcel.getExcelInfo(excelFile);
		if (packageList != null && !packageList.isEmpty()) {
			packageDao.batchInsert(packageList);
			/*for (int i=0; i<packageList.size(); i++) {
				packageDao.insert(packageList.get(i));
			}*/
			result = "导入成功";
		} else {
			result = "导入失败";
		}
		return result;
	}
}
