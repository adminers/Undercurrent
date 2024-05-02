package cn.afterturn.easypoi.lombok;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

/**
 * Created by jueyue on 18-1-30.
 */
public class ExcelLombokExport
{
    @Test
    public void test()
        throws Exception
    {
        List<MsgClient> list = new ArrayList<MsgClient>();
        for (int i = 0; i < 100; i++)
        {
            MsgClient client = new MsgClient();
            client.setBirthday(new Date());
            client.setClientName("小明" + i);
            client.setClientPhone("18797" + i);
            client.setRemark("测试" + i);
            list.add(client);
        }
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setFreezeCol(2);
        Workbook workbook = ExcelExportUtil.exportExcel(params, MsgClient.class, list);
        File savefile = new File("D:/home/excel/");
        if (!savefile.exists())
        {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/home/excel/ExcelLombokExport.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
