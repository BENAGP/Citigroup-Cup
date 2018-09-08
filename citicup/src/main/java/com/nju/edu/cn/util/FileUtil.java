package com.nju.edu.cn.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shea on 2018/9/8.
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 读入excel文件，解析后返回
     * @throws IOException
     */
    public static List<String[]> readExcel() throws IOException{
        File file = new File("/Users/shea/Downloads/futures_updating.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        //获得Workbook工作薄对象
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum;rowNum <= 10;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    logger.info(JSON.toJSONString(row));
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        logger.info(JSON.toJSONString(cell));
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        try {
            fileUtil.readExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
