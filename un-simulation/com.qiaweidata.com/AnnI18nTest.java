package cn.afterturn.easypoi.i18n;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.util.StopWatch;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.test.entity.temp.NumEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 国际化 注解里面的修改应用
 */
@Slf4j
public class AnnI18nTest
{
    @Test
    public void test()
        throws Exception
    {
        List<NumEntity> list = new ArrayList<NumEntity>();
        for (int i = 0; i < 20; i++)
        {
            NumEntity client = new NumEntity();
            client.setDouTest(i % 3 == 0 ? i + 0.0D : null);
            client.setIntTest((int)(Math.random() * 1000000000));
            client.setLongTest((long)(Math.random() * 100000000000000000L));
            client.setStrTest(Math.random() * 100000000000000000D + "");
            list.add(client);
        }
        StopWatch clock = new StopWatch();
        clock.start();
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setI18nHandler(name -> {
            switch (name)
            {
                // 这里可以环境选择对应的数据
                case "str":
                    return "字符串";
            }
            return name;
        });
        Workbook workbook = ExcelExportUtil.exportExcel(params, NumEntity.class, list);
        clock.stop();
        log.debug("use time is {}", clock.getTotalTimeMillis());
        File savefile = new File("D:/home/excel/");
        if (!savefile.exists())
        {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/home/excel/AnnI18nTest.test.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
