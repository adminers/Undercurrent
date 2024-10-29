package cn.afterturn.easypoi.csv.read;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.StopWatch;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import cn.afterturn.easypoi.test.entity.MsgClient;
import cn.afterturn.easypoi.test.excel.read.FileUtilTest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author by jueyue on 18-10-3.
 */
@Slf4j
public class CsvDataRead
{
    @Test
    public void test()
    {
        try
        {
            StopWatch clock = new StopWatch();
            clock.start();
            log.debug("start");
            CsvImportParams params = new CsvImportParams(CsvImportParams.GBK);
            params.setTitleRows(1);
            CsvImportUtil.importCsv(new FileInputStream(new File(FileUtilTest.getWebRootPath("csv/BigDataExport.csv"))), MsgClient.class, params, new IReadHandler()
            {
                @Override
                public void handler(Object o)
                {
                }
                
                @Override
                public void doAfterAll()
                {
                }
            });
            clock.stop();
            log.debug("end,time is {}", clock.getTotalTimeMillis());
        }
        catch (Exception e)
        {
        }
    }
    
    @Test
    public void testUtf8Bom()
    {
        try
        {
            StopWatch clock = new StopWatch();
            clock.start();
            log.debug("start");
            CsvImportParams params = new CsvImportParams(CsvImportParams.UTF8);
            CsvImportUtil.importCsv(new FileInputStream(new File(FileUtilTest.getWebRootPath("csv/20181107202743.csv"))), Map.class, params, new IReadHandler<Map>()
            {
                @Override
                public void handler(Map o)
                {
                }
                
                @Override
                public void doAfterAll()
                {
                }
            });
            clock.stop();
            log.debug("end,time is {}", clock.getTotalTimeMillis());
        }
        catch (Exception e)
        {
        }
    }
}
