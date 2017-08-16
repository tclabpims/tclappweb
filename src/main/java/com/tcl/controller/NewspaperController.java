package com.tcl.controller;

import com.tcl.model.NewspaperModel;
import com.tcl.service.NewspaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 广告
 */
@Controller
@RequestMapping("/newspaper")
public class NewspaperController {

	private static final int PAGE_SIZE = 5;

	@Autowired
	private NewspaperService newspaperService;

	/**
	 * 列出所有的新闻
	 * @param map
	 * @param request
	 * @param type
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("list")
	public String listPage(ModelMap map,HttpServletRequest request,@RequestParam String type, String pageNo){
		int page_no;
		boolean isEmpty = false;
		if (pageNo == null || pageNo == "") {
			page_no = 1;
		}
		else {
			page_no = Integer.parseInt(pageNo);
			if (page_no < 1) {
				page_no = 1;
			}
		}
		List<NewspaperModel> newspaper_list = newspaperService.selectByType(type);
		int total_page = (newspaper_list.size() + PAGE_SIZE - 1) / PAGE_SIZE;
		if (total_page < 1) {
			total_page = 1;
			isEmpty = true;
		}
		if(page_no > total_page) {
			page_no = total_page;
		}
		if(!isEmpty) {
			List<NewspaperModel> newspapers = newspaperService.selectByPageInfo(type, page_no, PAGE_SIZE);
			map.put("list", newspapers);
		} else {
			map.put("list", new ArrayList<NewspaperModel>());

		}
		map.put("type", type);
		map.put("pageNo", page_no);
		map.put("totalPage", total_page);
		return "newspaper/list";
	}

	/**
	 * 通过参数查询新闻
	 * @param map
	 * @param newspaperModel
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/query")
	public String queryNewspaper(ModelMap map, NewspaperModel newspaperModel, String pageNo) {
		int page_no;
		boolean isEmpty = false;
		if (pageNo == null || pageNo == "") {
			page_no = 1;
		}
		else {
			page_no = Integer.parseInt(pageNo);
			if (page_no < 1) {
				page_no = 1;
			}
		}
		List<NewspaperModel> newspaper_list = newspaperService.queryByInfo(newspaperModel);
		int total_page = (newspaper_list.size() + PAGE_SIZE - 1) / PAGE_SIZE;
		if (total_page < 1) {
			total_page = 1;
			isEmpty = true;
		}
		if(page_no > total_page) {
			page_no = total_page;
		}
		if (!isEmpty) {
			List<NewspaperModel> newspapers = newspaperService.queryByPageInfo(newspaperModel.getTitle(),
					newspaperModel.getType(),
					newspaperModel.getStatus(),
					page_no, PAGE_SIZE);
			map.put("list", newspapers);
		}else {
			map.put("list", new ArrayList<NewspaperModel>());
		}
		map.put("title", newspaperModel.getTitle());
		map.put("type", newspaperModel.getType());
		map.put("status", newspaperModel.getStatus());
		map.put("pageNo", page_no);
		map.put("totalPage", total_page);
		map.put("query_flag", true);
		return "newspaper/list";
	}

	/**
	 * 增加新闻
	 * @param newspaperModel
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addNewspaper(HttpServletRequest request,NewspaperModel newspaperModel) {
		newspaperModel.setCreateUserid((Long) request.getSession().getAttribute("userId"));
		newspaperModel.setCreateTime(new Date());
		Map<String, String> map = new HashMap<String, String>();
		int result = newspaperService.addNewspaper(newspaperModel);
		if (result > 0) {
			map.put("msg", "success");
		}else {
			map.put("msg", "error");
		}
		return map;
	}

	/**
	 * 通过ID删除某条记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteHospitalById(String id) {
		//对接收到的字符串参数进行基本的处理与判断
		id = id.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(id);
		int result;
		Map<String, String> map = new HashMap<String, String>();
		if(matcher.matches()) {
			result = newspaperService.deleteById(Long.parseLong(id));
			if(result > 0) {
				//返回1表示删除成功
				map.put("msg", "1");
			} else {
				//返回2表示删除失败
				map.put("msg", "2");
			}
		} else {
			//返回3表示参数有误
			map.put("msg", "3");
		}
		return map;
	}

	/**
	 * 通过ID查找条新闻
	 * @param id
	 * @return
	 */
	@RequestMapping("/acquire")
	@ResponseBody
	public Map<String, Object> selectById(String id) {
		//对接收到的字符串参数进行基本的处理与判断
		id = id.trim();
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher matcher = p.matcher(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (matcher.matches()) {
			map.put("newspaper", newspaperService.selectById(Long.parseLong(id)));
		} else {
			NewspaperModel newspaperModel = new NewspaperModel();
			newspaperModel.setTitle("paramIsError");
			map.put("newspaper", newspaperModel);
		}
		return map;
	}

	/**
	 * 对某条新闻的信息进行更新
	 * @param newspaperModel
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateById(NewspaperModel newspaperModel) {
		newspaperModel.setModifyTime(new Date());
		int result = newspaperService.updateById(newspaperModel);
		Map<String, String> map = new HashMap<String, String>();
		if (result > 0) {
			map.put("msg", "success");
		}else {
			map.put("msg", "error");
		}
		return map;
	}

	/**
	 * 上传文件
	 * @param request
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/content", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getNewsPaperPage(HttpServletRequest request, String content, String id) {
		String saveHtmlFileDir = null;
		String htmlUrl = null;
		Properties prop = new Properties();
		Map<String, String> map = new HashMap<String, String>();
		try {
			prop.load(NewspaperController.class.getClassLoader().getResourceAsStream("conf/config.properties"));
			saveHtmlFileDir = prop.getProperty("realHtmlPath");
			htmlUrl = prop.getProperty("htmlUrl");
		} catch (IOException e) {
			e.printStackTrace();
			map.put("msg", "无法获取文件路径");
			return map;
		}
		File filedir = new File(saveHtmlFileDir);
		//判断上传文件的保存目录是否存在
		if (!filedir.exists() && !filedir.isDirectory()) {
			System.out.println(saveHtmlFileDir+"目录不存在，需要创建");
			//创建目录
			filedir.mkdirs();
		}
		StringBuilder tempPath = new StringBuilder(saveHtmlFileDir);
		tempPath.append("/");
		SimpleDateFormat folderNameFormat = new SimpleDateFormat("yyyyMMdd");
		tempPath.append(folderNameFormat.format(new Date()));
		File temp = new File(tempPath.toString());
		if(!temp.exists()) temp.mkdirs();
		SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddkkmmss_S");
		tempPath.append("/").append(fileNameFormat.format(new Date()));
		tempPath.append(".").append("html");
		String htmlFilePath = tempPath.toString().replaceAll("\\\\", "/");
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(htmlFilePath), "UTF-8");
			bw = new BufferedWriter(osw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			map.put("msg", "error");
			return map;
		} finally {
			if(bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		int point = htmlFilePath.lastIndexOf("/") - 8;
		StringBuilder url = new StringBuilder(htmlUrl);
		url.append("/");
		url.append(htmlFilePath.substring(point));
		NewspaperModel newspaperModel = new NewspaperModel();
		newspaperModel.setId(Long.parseLong(id.trim()));
		newspaperModel.setContentUrl(url.toString());
		int result = newspaperService.updateById(newspaperModel);
		if (result > 0) {
			map.put("msg", "success");
		} else {
			map.put("msg", "内容链接更新失败");
		}
		return map;
	}
}
