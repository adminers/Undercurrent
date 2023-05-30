package com.qiaweidata.un.poi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @Title: TestCode
 * @Description: TestCode
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-03-07
 * @version: V1.0
 */
public class TestCode {

    private static Logger log = LogManager.getLogManager().getLogger("");

    private static String fileName = "C:\\Users\\Administrator\\Documents\\WXWork\\1688850839526306\\Cache\\File"
        + "\\2023-03\\客户信息维护-OK.xlsx";

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {

        
        
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1

        if (false) {
            // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
            // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
            EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
                for (DemoData demoData : dataList) {
                    log.info("读取到一条数据{}" + new Gson().toJson(demoData));
                }
            }, 1000)).sheet("开户补录").headRowNumber(Integer.valueOf(3)).doRead();
            return;
        }

        long startTime = System.currentTimeMillis();


        // 写法2：
        // 匿名内部类 不用额外写一个DemoDataListener
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, new ReadListener<Map<Integer,String>>() {

            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100000;

            /**
             *临时存储
             */
            private List<Map<Integer,String>> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(Map<Integer,String> data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    //saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            @Override
            public boolean hasNext(AnalysisContext context) {

                if (cachedDataList.size() > BATCH_COUNT) {
                    return false;
                }
                return true;
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                log.info("{}条数据，开始存储数据库！" +  cachedDataList.size());
                log.info("存储数据库成功！");
            }
        }).sheet("开户补录").headRowNumber(Integer.valueOf(3)).doRead();

        System.out.println("----shenshilong------" + (System.currentTimeMillis() - startTime) + " ms.");
        if (true) {
            return;
        }

        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法3：
        fileName = "testPath" + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

        // 写法4
        fileName = "testPath" + "demo" + File.separator + "demo.xlsx";
        // 一个文件一个reader
        try (ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }
    }

    @Test
    public void wr() {

        List<Map<String, String>> allData = new ArrayList<>();
        Map<String, Integer> excelIndex = new HashMap<>();
        excelIndex.put("ID", Integer.valueOf(0));
        excelIndex.put("I0000005", Integer.valueOf(1));
        excelIndex.put("I0000006", Integer.valueOf(2));
        excelIndex.put("I0000007", Integer.valueOf(3));
        excelIndex.put("TASKDETAILID", Integer.valueOf(4));

        List<String> columns = new ArrayList<>();
        columns.add("ID");
        columns.add("I0000005");
        columns.add("I0000006");
        columns.add("I0000007");
        columns.add("TASKDETAILID");

        long startTime = System.currentTimeMillis();
        work(allData, excelIndex, columns);
        log.info("----shenshilong------{} ms." + (System.currentTimeMillis() - startTime));
        log.info("结束");
    }

    private void work(List<Map<String, String>> allData, Map<String, Integer> excelIndex,
        List<String> columns) {

        int startRow = 10;
        int startEnd = 20;
        int easyStartRow = startRow - 1;
        final int columnSize = columns.size();
        AtomicInteger tempCount = new AtomicInteger(0);
        final int easyEndRow = startEnd - easyStartRow;
        EasyExcel.read(fileName, new ReadListener<Map<Integer,String>>() {

            @Override
            public void invoke(Map<Integer,String> data, AnalysisContext context) {
                tempCount.incrementAndGet();
                Map<String, String> rowData = new HashMap<>(columnSize);
                columns.forEach(column -> rowData.put(column, data.get(excelIndex.get(column))));
                allData.add(rowData);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            @Override
            public boolean hasNext(AnalysisContext context) {

                if (tempCount.get() == easyEndRow) {
                    return false;
                }
                return true;
            }

            private void saveData() {
                log.info("解析结束！");
            }
        }).sheet("开户补录").headRowNumber(Integer.valueOf(easyStartRow)).doRead();

    }

    @Test
    public void 文件最后一行() {

        ZipSecureFile.setMinInflateRatio(-1.0d);
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\Administrator\\Documents\\WXWork\\1688850839526306\\Cache\\File\\2023-05\\上报数据.xlsx")));) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
