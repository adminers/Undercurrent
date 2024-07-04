package cn.afterturn.easypoi.test.excel.read;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.test.entity.MsgClient;

/**
 * @author JueYue on 2017/12/6.
 */
public class BigDataRead
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BigDataRead.class);
    
    @Test
    public void test()
    {
        try
        {
            LOGGER.debug("start");
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            List<MsgClient> result = ExcelImportUtil.importExcel(new File(FileUtilTest.getWebRootPath("import/BigDataExport.xlsx")), MsgClient.class, params);
            Assert.assertTrue(result.size() == 200000);
        }
        catch (Exception e)
        {
        }
    }
    
    @Test
    public void test2000()
    {
        try
        {
            LOGGER.debug("start");
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            List<MsgClient> result = ExcelImportUtil.importExcel(new File(FileUtilTest.getWebRootPath("import/BigDataExport20000.xlsx")), MsgClient.class, params);
            Assert.assertTrue(result.size() == 20000);
        }
        catch (Exception e)
        {
        }
    }
    
    @Test
    public void test5000()
    {
        try
        {
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            List<MsgClient> result = ExcelImportUtil.importExcel(new File(FileUtilTest.getWebRootPath("import/BigDataExport50000.xlsx")), MsgClient.class, params);
            Assert.assertTrue(result.size() == 50000);
        }
        catch (Exception e)
        {
        }
    }
}
