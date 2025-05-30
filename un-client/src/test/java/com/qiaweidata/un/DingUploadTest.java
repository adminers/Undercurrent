package com.qiaweidata.un;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 13533702421637642870
 * 都不知道文件上传到哪里去了
 *
 * @Title: DingUploadTest
 * @Description: DingUploadTest
 * @date: 2023-04-07
 * @version: V1.0
 */
public class DingUploadTest {

    public static final Map<String, String> properties = new HashMap<>(2);

    public static final String token = "d7fd52649e213d01b3608157ca8a1f18";

    static {

        //读取resources/config目录下的application.properties
        ResourceBundle rb2 = ResourceBundle.getBundle("ding");
        for(String key : rb2.keySet()){
            String value = rb2.getString(key);
            properties.put(key, value);
        }
    }

    public void getApiInfo() {

    }

    public void singleFileUpload() throws IOException {

        // 从接口返回信息中拿到resourceUrls
        String resourceUrl = "第一步接口返回的resourceUrls";
        // 从接口返回信息中拿到headers
        Map<String, String> headers = null;//"第一步接口返回的headers";
        URL url = new URL(resourceUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setUseCaches(false);
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        connection.connect();
        OutputStream out = connection.getOutputStream();
        InputStream is = new FileInputStream(new File("/Users/xxxxx/Desktop/测试文件.xls"));
        byte[] b =new byte[1024];
        int temp;
        while ((temp=is.read(b))!=-1){
            out.write(b,0,temp);
        }
        out.flush();
        out.close();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        if (responseCode == 200) {
            System.out.println("上传成功");
        } else {
            System.out.println("上传失败");
        }
    }

    public void corpId() {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("corpid", properties.get("corpid"));
        paramMap.put("ssoSecret", properties.get("ssosecret"));

        //String result= HttpUtil..post("https://api.dingtalk.com/v1.0/oauth2/ssoAccessToken", paramMap);
        //链式构建请求
        String result2 = HttpRequest.post("https://api.dingtalk.com/v1.0/oauth2/ssoAccessToken")
            .header(Header.CONTENT_TYPE, "application/json")//头信息，多个头信息多次调用此方法即可
            .form(paramMap)//表单内容
            .timeout(20000)//超时，毫秒
            .execute().body();
        Console.log(result2);
        System.out.println(result2);
    }

    public static void main(String[] args) {
        getToken2();

    }

    private static void testOcr() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("image_url", "https://ai.bdstatic.com/file/467F4DD90B8B4B8F9B4869AA7DFA88E0");
        paramMap.put("type", "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic");
        paramMap.put("detect_direction", false);
        paramMap.put("language_type", "CHN_ENG");
        String url = "https://ai.baidu.com/aidemo?image&image_url=https://ai.bdstatic.com/file/D9050B1BEB5A49508D882D3D652F3D38&type="
            + "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic&detect_direction=false&language_type=CHN_ENG";
        String json = new Gson().toJson(paramMap);
        String result2 = HttpRequest.post(url)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Tracecode", "18069313750412522250040817")
            .header("Transfer-Encoding", "chunked")
            .header("X-Protected-By", "OpenRASP")
            .header("Access-Control-Allow-Origin", "*")
            //.body(json)
            .execute().body();
        Console.log(result2);
    }

    private static void getToken() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("corpid", properties.get("corpid"));
        paramMap.put("ssoSecret", properties.get("ssosecret"));

        //String result= HttpUtil..post("https://api.dingtalk.com/v1.0/oauth2/ssoAccessToken", paramMap);
        //链式构建请求
       /* String result2 = HttpRequest.post("https://api.dingtalk.com/v1.0/oauth2/ssoAccessToken")
            .header(Header.CONTENT_TYPE, "application/json")//头信息，多个头信息多次调用此方法即可
            .form(paramMap)//表单内容
            .timeout(20000)//超时，毫秒
            .execute().body();
        */
        String url = "https://api.dingtalk.com/v1.0/oauth2/ssoAccessToken";

        String json = new Gson().toJson(paramMap);
        String result2 = HttpRequest.post(url)
            .body(json)
            .execute().body();
        Console.log(result2);

        // 2023-4-8 16:16:08 {"expireIn":7200,"accessToken":"86fd508de51f3aae89b49eaea952932c"}
        // 2023-4-10 11:53:09{"expireIn":7200,"accessToken":"0315cf68a1fa303ba32905f020f5b460"}
        // 2023-4-10 13:52:21{"expireIn":7200,"accessToken":"0315cf68a1fa303ba32905f020f5b460"}
        // 2023-4-10 14:00:19{"expireIn":7200,"accessToken":"0315cf68a1fa303ba32905f020f5b460"}

    }

    private static void getToken2() {
        String url = "https://oapi.dingtalk.com/gettoken?appkey=" + properties.get("appkey") + "&appsecret=" + properties.get("appsecret");
        String result2 = HttpRequest.get(url)
            .execute().body();
        Console.log(result2);

        // 2023-4-10 15:10:37 {"errcode":0,"access_token":"ad08973c6da4363a916ae0e4db388a22","errmsg":"ok","expires_in":7200}
        // 2023-4-11 10:14:03 {"errcode":0,"access_token":"464c28c4cf733ab5b2e4014d30299eb5","errmsg":"ok","expires_in":7200}
        // 2023-4-12 16:06:55 {"errcode":0,"access_token":"d7fd52649e213d01b3608157ca8a1f18","errmsg":"ok","expires_in":7200}
    }

    private static void getUserInfo() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("language", "zh_CN");
        paramMap.put("userid", "13533702421637642870");
        String url = "https://oapi.dingtalk.com/topapi/v2/user/get?access_token=" + token;
        String json = new Gson().toJson(paramMap);
        String result2 = HttpRequest.post(url)
            .body(json)
            .execute().body();

        Console.log(result2);
    }

    private static void getSpace() {

        String url = "https://api.dingtalk.com/v1.0/storage/spaces?unionId=" + properties.get("unionid");
        String result2 = HttpRequest.post(url)
            .header("x-acs-dingtalk-access-token", token)
            .execute().body();

        Console.log(result2);
    }

}
