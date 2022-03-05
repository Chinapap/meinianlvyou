package com.yinjing;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author尹晶
 * @Date2022/3/4 22:54
 * @Version 1.0
 */
public class TestPoi {
    @Test
    public void readExcel() throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\hello2.xlsx");
        //获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        //遍历工作表获得行对象
        for (Row row : sheet) {
            //遍历行对象获取单元格对象
            for (Cell cell :
                    row) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                //获取单元格中的值
                String value = cell.getStringCellValue();
                System.out.print(value);
            }
        }
    }

    @Test
    public void createExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表，指定工作表名称
        XSSFSheet sheet = workbook.createSheet("工资单");

        //创建行， 0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("姓名");

        for (int i = 0; i < 500; i++) {
            XSSFRow rows = sheet.createRow(i + 1);
            rows.createCell(0).setCellValue(i + 1);
            rows.createCell(1).setCellValue(i + 10);
            rows.createCell(2).setCellValue(UUID.randomUUID().toString().substring(1,5));
        }
        FileOutputStream fos = new FileOutputStream("D:\\hello2.xlsx");
        workbook.write(fos);


    }

}
