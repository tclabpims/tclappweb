package com.tcl.utils.excel.impl;

import com.tcl.model.PackageModelWithBLOBs;
import com.tcl.model.UserModel;
import com.tcl.utils.StringUtil;
import com.tcl.utils.excel.ReadExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class ReadPackageExcel implements ReadExcel{

    //总行数
    private int totalRows;
    //总列数
    private int totalCells;
    //错误信息接收器
    private String errorMsg;

    public int getTotalRows() {return totalRows;}

    public int getTotalCells() {return totalCells;}

    public String getErrorMsg() {return errorMsg;}

    public List<PackageModelWithBLOBs> getExcelInfo(MultipartFile excelFile) {
        String filename = excelFile.getOriginalFilename();
        List<PackageModelWithBLOBs> packageList = null;
        try {
            if (!validateExcel(filename)) {
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(filename)) {
                isExcel2003 = false;
            }

            packageList = createExcel(excelFile.getInputStream(), isExcel2003);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packageList;
    }

    private List<PackageModelWithBLOBs> createExcel(InputStream inputStream, boolean isExcel2003) {
        List<PackageModelWithBLOBs> packageList = null;
        Workbook wb = null;
        try {
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            }
            else {
                wb = new XSSFWorkbook(inputStream);
            }
            packageList = readExcelValue(wb);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return packageList;
    }

    private List<PackageModelWithBLOBs> readExcelValue(Workbook wb) {
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        //得到Excel的列数(前提是有行数)
        if(totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<PackageModelWithBLOBs> packageList = new ArrayList<PackageModelWithBLOBs>();
        for(int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            PackageModelWithBLOBs packageModel = new PackageModelWithBLOBs();
            //循环Excel列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        String testType = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            testType = String.valueOf(cell.getNumericCellValue());
                            testType = testType.substring(0, testType.length() - 2 > 0 ? testType.length() - 2 : 1);
                        } else {
                            testType = cell.getStringCellValue();
                        }
                        packageModel.setTestType(testType);
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
                        packageModel.setName(name);
                    }
                    if (c == 3) {
                        String useCrowd = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            useCrowd = String.valueOf(cell.getNumericCellValue());
                            useCrowd = useCrowd.substring(0, useCrowd.length() - 2 > 0 ? useCrowd.length() - 2 : 1);
                        } else {
                            useCrowd = cell.getStringCellValue();
                        }
                        packageModel.setUseCrowd(useCrowd);
                    }
                    if (c == 4) {
                        Long price  = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String price_str = String.valueOf(cell.getNumericCellValue());
                            price_str = price_str.substring(0, price_str.length() - 2 > 0 ? price_str.length() - 2 : 1);
                            price = StringUtil.priceProcess(price_str);
                        } else {
                            String price_str = cell.getStringCellValue();
                            if (!price_str.isEmpty() && price_str != null) {
                                price = StringUtil.priceProcess(price_str);
                            }
                        }
                        packageModel.setPrice(price);
                    }
                    if (c == 5) {
                        String needAttention = null;
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            needAttention = String.valueOf(cell.getNumericCellValue());
                            needAttention = needAttention.substring(0, needAttention.length() - 2 > 0 ? needAttention.length() - 2 : 1);
                        } else {
                            needAttention = cell.getStringCellValue();
                        }
                        packageModel.setNeedAttention(needAttention);
                    }
                    if (c == 6) {
                        String projectDesc = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            projectDesc = String.valueOf(cell.getNumericCellValue());
                            projectDesc = projectDesc.substring(0, projectDesc.length() - 2 > 0 ? projectDesc.length() - 2 : 1);
                        } else {
                            projectDesc = cell.getStringCellValue();
                        }
                        packageModel.setProjectDesc(projectDesc);
                    }
                    if (c == 7) {
                        String reportTimeDesc = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            reportTimeDesc = String.valueOf(cell.getNumericCellValue());
                            reportTimeDesc = reportTimeDesc.substring(0, reportTimeDesc.length() - 2 > 0 ? reportTimeDesc.length() - 2 : 1);
                        } else {
                            reportTimeDesc = cell.getStringCellValue();
                        }
                        packageModel.setReportTimeDesc(reportTimeDesc);
                    }
                }
            }
            packageModel.setSaleNum(0);
            packageList.add(packageModel);
        }
        return packageList;
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
