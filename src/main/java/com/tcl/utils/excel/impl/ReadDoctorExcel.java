package com.tcl.utils.excel.impl;

import com.tcl.model.DoctorModel;
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
public class ReadDoctorExcel implements ReadExcel{

    //总行数
    private int totalRows;
    //总列数
    private int totalCells;
    //错误信息接收器
    private String errorMsg;

    public int getTotalRows() {return totalRows;}

    public int getTotalCells() {return totalCells;}

    public String getErrorMsg() {return errorMsg;}

    public List<DoctorModel> getExcelInfo(MultipartFile excelFile) {
        String filename = excelFile.getOriginalFilename();
        List<DoctorModel> doctorList = null;
        try {
            if (!validateExcel(filename)) {
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(filename)) {
                isExcel2003 = false;
            }

            doctorList = createExcel(excelFile.getInputStream(), isExcel2003);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    private List<DoctorModel> createExcel(InputStream inputStream, boolean isExcel2003) {
        List<DoctorModel> doctorList = null;
        Workbook wb = null;
        try {
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            }
            else {
                wb = new XSSFWorkbook(inputStream);
            }
            doctorList = readExcelValue(wb);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return doctorList;
    }

    private List<DoctorModel> readExcelValue(Workbook wb) {
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        //得到Excel的列数(前提是有行数)
        if(totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
        for(int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            DoctorModel doctorModel = new DoctorModel();
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
                        doctorModel.setUserName(username);
                    }
                    if (c == 1) {
                        String doctorName = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            doctorName = String.valueOf(cell.getNumericCellValue());
                            doctorName = doctorName.substring(0, doctorName.length() - 2 > 0 ? doctorName.length() - 2 : 1);
                        } else {
                            doctorName = cell.getStringCellValue();
                        }
                        doctorModel.setDoctorName(doctorName);
                    }
                    if (c == 2) {
                        Integer age = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String age_str = String.valueOf(cell.getNumericCellValue());
                            age_str = age_str.substring(0, age_str.length() - 2 > 0 ? age_str.length() - 2 : 1);
                            age = Integer.parseInt(age_str);
                        } else {
                            String age_str = cell.getStringCellValue();
                            if (!age_str.isEmpty() && age_str != null) {
                                age = Integer.parseInt(age_str);
                            }

                        }
                        doctorModel.setAge(age);
                    }
                    if (c == 3) {
                        String sex = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            sex = String.valueOf(cell.getNumericCellValue());
                            sex = sex.substring(0, sex.length() - 2 > 0 ? sex.length() - 2 : 1);
                        } else {
                            sex = cell.getStringCellValue();
                        }
                        doctorModel.setSex(sex);
                    }
                    if (c == 4) {
                        String sfzNum  = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            sfzNum = String.valueOf(cell.getNumericCellValue());
                            sfzNum = sfzNum.substring(0, sfzNum.length() - 2 > 0 ? sfzNum.length() - 2 : 1);
                        } else {
                            sfzNum = cell.getStringCellValue();
                        }
                        doctorModel.setSfzNum(sfzNum);
                    }


                    if (c == 5) {
                        String education = null;
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            education = String.valueOf(cell.getNumericCellValue());
                            education = education.substring(0, education.length() - 2 > 0 ? education.length() - 2 : 1);
                        } else {
                            education = cell.getStringCellValue();
                        }
                        doctorModel.setEducation(education);
                    }
                    if (c == 6) {
                        String title = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            title = String.valueOf(cell.getNumericCellValue());
                            title = title.substring(0, title.length() - 2 > 0 ? title.length() - 2 : 1);
                        } else {
                            title = cell.getStringCellValue();
                        }
                        doctorModel.setTitle(title);
                    }
                    if (c == 7) {
                        Long hospitaId  = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String hospital_str = String.valueOf(cell.getNumericCellValue());
                            hospital_str = hospital_str.substring(0, hospital_str.length() - 2 > 0 ? hospital_str.length() - 2 : 1);
                            hospitaId = Long.parseLong(hospital_str);
                        } else {
                            String hospital_str = cell.getStringCellValue();
                            if (!hospital_str.isEmpty() && hospital_str != null) {
                                hospitaId = Long.parseLong(hospital_str);
                            }
                        }
                        doctorModel.setHospitalId(hospitaId);
                    }
                    if (c == 8) {
                        String hospitalName = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            hospitalName = String.valueOf(cell.getNumericCellValue());
                            hospitalName = hospitalName.substring(0, hospitalName.length() - 2 > 0 ? hospitalName.length() - 2 : 1);
                        } else {
                            hospitalName = cell.getStringCellValue();
                        }
                        doctorModel.setHospitalName(hospitalName);
                    }
                    if (c == 9) {
                        String departmentNum = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            departmentNum = String.valueOf(cell.getNumericCellValue());
                            departmentNum = departmentNum.substring(0, departmentNum.length() - 2 > 0 ? departmentNum.length() - 2 : 1);
                        } else {
                            departmentNum = cell.getStringCellValue();
                        }
                        doctorModel.setDepartmentNum(departmentNum);
                    }
                    if (c == 10) {
                        String departmentName = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            departmentName = String.valueOf(cell.getNumericCellValue());
                            departmentName = departmentName.substring(0, departmentName.length() - 2 > 0 ? departmentName.length() - 2 : 1);
                        } else {
                            departmentName = cell.getStringCellValue();
                        }
                        doctorModel.setDepartmentName(departmentName);
                    }
                    if (c == 11) {
                        String zzNum = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            zzNum = String.valueOf(cell.getNumericCellValue());
                            zzNum = zzNum.substring(0, zzNum.length() - 2 > 0 ? zzNum.length() - 2 : 1);
                        } else {
                            zzNum = cell.getStringCellValue();
                        }
                        doctorModel.setZzNum(zzNum);
                    }
                    if (c == 12) {
                        String zcNum = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            zcNum = String.valueOf(cell.getNumericCellValue());
                            zcNum = zcNum.substring(0, zcNum.length() - 2 > 0 ? zcNum.length() - 2 : 1);
                        } else {
                            zcNum = cell.getStringCellValue();
                        }
                        doctorModel.setZcNum(zcNum);
                    }
                    if (c == 13) {
                        String type = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            type = String.valueOf(cell.getNumericCellValue());
                            type = type.substring(0, type.length() - 2 > 0 ? type.length() - 2 : 1);
                        } else {
                            type = cell.getStringCellValue();
                        }
                        if (type.equals("医生")) doctorModel.setType("1");
                        if (type.equals("护士")) doctorModel.setType("2");
                    }
                    if (c == 14) {
                        String position = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            position = String.valueOf(cell.getNumericCellValue());
                            position = position.substring(0, position.length() - 2 > 0 ? position.length() - 2 : 1);
                        } else {
                            position = cell.getStringCellValue();
                        }
                        doctorModel.setPosition(position);
                    }
                    if (c == 15) {
                        String introduce = null;
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            introduce = String.valueOf(cell.getNumericCellValue());
                            introduce = introduce.substring(0, introduce.length() - 2 > 0 ? introduce.length() - 2 : 1);
                        } else {
                            introduce = cell.getStringCellValue();
                        }
                        doctorModel.setIntroduce(introduce);
                    }
                }
            }
            doctorModel.setCreateTime(new Date());
            doctorModel.setReadReportNum(0);
            doctorModel.setDiagnosisNum(0);
            doctorModel.setIsOpenAutoreceipt("NO");
            doctorModel.setReceiptInterval(0);
            doctorModel.setStatus("2");
            doctorList.add(doctorModel);
        }
        return doctorList;
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
