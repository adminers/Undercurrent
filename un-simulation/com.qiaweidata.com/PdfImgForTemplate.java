package cn.afterturn.easypoi.test.pdf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.itextpdf.layout.Document;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.pdf.PdfExportUtil;
import cn.afterturn.easypoi.test.word.entity.Person;

/**
 * @author jueyue on 20-8-22.
 */
public class PdfImgForTemplate
{
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
    
    @Test
    public void imageWordExport()
        throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("time", format.format(new Date()));
        Person person = new Person();
        person.setName("JueYue");
        map.put("p", person);
        ImageEntity image = new ImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        image.setUrl("imgs/word/testCode.png");
        image.setType(ImageEntity.URL);
        map.put("testCode", image);
        List<ImageEntity> listImage = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            image = new ImageEntity();
            image.setHeight(40);
            image.setWidth(100);
            image.setUrl("https://pic.xlhall.com/FnVdGOVJiIYVPImX4-KKe1Q_wUQ2?tdsourcetag=s_pctim_aiomsg");
            image.setType(ImageEntity.URL);
            listImage.add(image);
        }
        map.put("imglist", listImage);
        FileOutputStream fos = new FileOutputStream("D:/home/excel/imgfor.pdf");
        Document doc = PdfExportUtil.exportPdf("pdf/imgfor.pdf", map, fos);
        fos.close();
    }
}
