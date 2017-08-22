package com.tcl.utils.excel.impl;

import com.tcl.model.UserModel;
import com.tcl.utils.StringUtil;
import com.tcl.utils.excel.ReadExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LiuQi on 2017/8/19.
 */
public class ReadUserExcel implements ReadExcel{

    //总行数
    private int totalRows;
    //总列数
    private int totalCells;
    //错误信息接收器
    private String errorMsg;

    public int getTotalRows() {return totalRows;}

    public int getTotalCells() {return totalCells;}

    public String getErrorMsg() {return errorMsg;}

    public List<UserModel> getExcelInfo(MultipartFile excelFile) {
        String filename = excelFile.getOriginalFilename();
        List<UserModel> userList = null;
        try {
            if (!validateExcel(filename)) {
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(filename)) {
                isExcel2003 = false;
            }

            userList = createExcel(excelFile.getInputStream(), isExcel2003);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private List<UserModel> createExcel(InputStream inputStream, boolean isExcel2003) {
        List<UserModel> userList = null;
        Workbook wb = null;
        try {
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            }
            else {
                wb = new XSSFWorkbook(inputStream);
            }
            userList = readExcelValue(wb);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return userList;
    }

    private List<UserModel> readExcelValue(Workbook wb) {
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        //得到Excel的列数(前提是有行数)
        if(totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<UserModel> userList = new ArrayList<UserModel>();
        for(int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            UserModel userModel = new UserModel();
            //循环Excel列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        String username = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            username = String.valueOf(cell.getNumericCellValue());
                            username = username.substring(0, username.length() - 2 > 0 ? username.length() - 2 : 1);
                        } else {
                            username = cell.getStringCellValue();
                        }
                        userModel.setUserName(username);
                    }
                    if (c == 1) {
                        String name = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            name = String.valueOf(cell.getNumericCellValue());
                            name = name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1);
                        } else {
                            name = cell.getStringCellValue();
                        }
                        userModel.setName(name);
                    }
                    if (c == 2) {
                        String sex = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            sex = String.valueOf(cell.getNumericCellValue());
                            sex = sex.substring(0, sex.length() - 2 > 0 ? sex.length() - 2 : 1);
                        } else {
                            sex = cell.getStringCellValue();
                        }
                        userModel.setSex(sex);
                    }
                    if (c == 3) {
                        String sfzNum  = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            sfzNum = String.valueOf(cell.getNumericCellValue());
                            sfzNum = sfzNum.substring(0, sfzNum.length() - 2 > 0 ? sfzNum.length() - 2 : 1);
                        } else {
                            sfzNum = cell.getStringCellValue();
                        }
                        userModel.setSfzNum(sfzNum);
                    }
                    if (c == 4) {
                        Date birthDay = null;
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            birthDay = cell.getDateCellValue();
                        } else {
                            String string = cell.getStringCellValue();
                            if (!string.isEmpty() && string != null) {
                                birthDay = StringUtil.getDate(string, "yyyy-MM-dd");
                            }
                        }
                        userModel.setBirthday(birthDay);
                    }
                    if (c == 5) {
                        String address = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            address = String.valueOf(cell.getNumericCellValue());
                            address = address.substring(0, address.length() - 2 > 0 ? address.length() - 2 : 1);
                        } else {
                            address = cell.getStringCellValue();
                        }
                        userModel.setAddress(address);
                    }
                }
            }
            userModel.setCreateTime(new Date());
            userModel.setStatus("1");
            userList.add(userModel);
        }
        return userList;
    }

    private boolean validateExcel(String filename) {
        if (filename == null || !(isExcel2003(filename) || isExcel2007(filename))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
