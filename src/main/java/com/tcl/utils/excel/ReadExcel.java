package com.tcl.utils.excel;

import com.tcl.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by LiuQi on 2017/8/19.
 */
public interface ReadExcel {

    List getExcelInfo(MultipartFile excelFile);
}
