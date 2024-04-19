package cn.afterturn.easypoi.wps;

import java.io.InputStream;

import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Service;

import cn.afterturn.easypoi.cache.manager.FileLoaderImpl;
import cn.afterturn.easypoi.wps.service.IEasyPoiWpsService;

/**
 * @author jueyue on 20-5-8.
 */
@Service
public class WpsService implements IEasyPoiWpsService
{
    @Override
    public String getAppSecret()
    {
        return "";
    }
    
    @Override
    public String getAppId()
    {
        return "";
    }
    
    @Override
    public int getFileSize(String filepath)
    {
        InputStream is = null;
        try
        {
            is = FileLoaderImpl.class.getClassLoader().getResourceAsStream("exceltohtml/" + filepath);
            return is.available();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
        return 2048;
    }
    
    @Override
    public String getDownLoadUrl(String filepath)
    {
        return "http://easypoi.wupaas.com/" + filepath;
    }
}
