package com.nju.edu.cn.util;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.dao.FuturesRepository;
import com.nju.edu.cn.dao.FuturesUpdatingRepository;
import com.nju.edu.cn.dao.SpotGoodsRepository;
import com.nju.edu.cn.dao.SpotGoodsUpdatingRepository;
import com.nju.edu.cn.entity.Futures;
import com.nju.edu.cn.entity.FuturesUpdating;
import com.nju.edu.cn.entity.SpotGoods;
import com.nju.edu.cn.entity.SpotGoodsUpdating;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;

/**
 * Created by shea on 2018/9/8.
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    @Autowired
    private SpotGoodsRepository spotGoodsRepository;
    @Autowired
    private FuturesRepository futuresRepository;
    @Autowired
    private SpotGoodsUpdatingRepository spotGoodsUpdatingRepository;
    @Autowired
    private FuturesUpdatingRepository futuresUpdatingRepository;

    /**
     * 读入excel文件，解析后返回
     * @throws IOException
     */
    public void readExcel() throws IOException{
        File file = new File("/Users/shea/Downloads/test.xlsx");
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            //得到指定的单元格
            Cell cell = row.getCell(0);
            //得到单元格样式
            CellStyle cellStyle = cell.getCellStyle();
            logger.info("行数：" + rowLength + ",列数：" + colLength);
            for (int i = 0; i < rowLength; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
                    //Cannot get a STRING value from a NUMERIC cell
                    //将所有的需要读的Cell表格设置为String格式
                    if (cell != null)
                        cell.setCellType(CellType.STRING);

                    //对Excel进行修改
                    if (i > 0 && j == 1)
                        cell.setCellValue("1000");
                    System.out.print(cell.getStringCellValue() + "\t");
                }
                System.out.println();
            }

            //将修改好的数据保存
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readCsv(){
        File csv = new File("/Users/shea/Downloads/futures_updating.csv");  // CSV文件路径
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        int count = 0;
        try {
            List<String> allString = new ArrayList<>();
            String[] strings;
            int interestRate = 0;
            int price = 1;
            int trading = 2;
            int futuresId = 3;
            int updateTime = 4;
            List<SpotGoodsUpdating> spotGoodsUpdatings = new ArrayList<>();
            List<FuturesUpdating> futuresUpdatings = new ArrayList<>();
            List<SpotGoods> spotGoods = spotGoodsRepository.findAll();
            List<Futures> futuresList = futuresRepository.findAll();
            Map<Long,Futures> map = new TreeMap<>();
            futuresList.forEach(futures -> {
                map.put(futures.getFuturesId(),futures);
            });
            boolean isNaN = false;
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                strings = line.split(",");
                if(strings[futuresId].equals("1")||strings[futuresId].equals("10")){
                    for (int i=1;i<5;i++) {
                        if(strings[i].equals("NaN")){
                            isNaN = true;
                        }
                    }
                    if(isNaN){
                        isNaN = false;
                        break;
                    }
                    SpotGoodsUpdating spotGoodsUpdating = new SpotGoodsUpdating();
                    spotGoodsUpdating.setPrice(Float.valueOf(strings[price]));
                    spotGoodsUpdating.setUpdateTime(new Date(Long.valueOf(strings[updateTime])));
                    spotGoodsUpdating.setTrading(Integer.valueOf(strings[trading]));
                    if(strings[futuresId].equals("1"))spotGoodsUpdating.setSpotGoods(spotGoods.get(0));
                    else if(strings[futuresId].equals("10"))spotGoodsUpdating.setSpotGoods(spotGoods.get(1));
                    spotGoodsUpdatings.add(spotGoodsUpdating);
                }else {
                    for (int i=0;i<5;i++) {
                        if(strings[i].equals("NaN")){
                            isNaN = true;
                        }
                    }
                    if(isNaN){
                        isNaN = false;
                        break;
                    }
                    FuturesUpdating futuresUpdating = new FuturesUpdating();
                    futuresUpdating.setPrice(Float.valueOf(strings[price]));
                    futuresUpdating.setUpdateTime(new Date(Long.valueOf(strings[updateTime])));
                    futuresUpdating.setTrading(Integer.valueOf(strings[trading]));
                    futuresUpdating.setInterestRate(Double.valueOf(strings[interestRate]));
                    futuresUpdating.setFutures(map.get(Long.valueOf(strings[futuresId])));
                    futuresUpdatings.add(futuresUpdating);
                }

            }
            futuresUpdatingRepository.saveAll(futuresUpdatings);
            spotGoodsUpdatingRepository.saveAll(spotGoodsUpdatings);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        fileUtil.readCsv();
    }
}
