package com.qiaweidata.un.dingding;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.qiaweidata.un.DingUploadTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qiaweidata.un.DingUploadTest.token;

/**
 * 服务端API文档/文件存储管理文件传输文件上传问题自查手册
 * 文件上传问题自查手册
 * 更新于 2023-03-08
 * 本文主要介绍如何自查文件上传遇到的常见问题。
 *
 * 提交文件失败
 * 文件的上传有三个步骤，请参考获取文件上传信息文档内的步骤。
 *
 * 获取文件上传信息。
 *
 * 通过HTTP将文件上传到OSS。
 *
 * 提交文件。
 *
 * 需要确认下第二步将文件上传到OSS有没有成功返回200。
 *
 * 上传文件到OSS失败问题
 * 排查下代码问题，将<authorization>、<x-oss-date>、<url>替换成第一步获取文件上传信息接口返回的值。a.txt替换成本地存在的文件，执行以下命令。
 *
 * 1234
 * curl -v -T a.txt \
 * --header "Authorization: <authorization>" \
 * --header "x-oss-date: <x-oss-date>" \
 * --url "<url>"
 * 如果curl返回RequestTimeTooSkewed错误，表示上传信息过期，重新获取文件上传信息。
 *
 * 如果curl返回200，说明是代码问题。文档中已经有对应语言的demo，参照对应示例，排查代码问题。
 *
 * 如果代码上传返回SignatureDoesNotMatch，大概率是HTTP请求headers多传了contentType，主动设置为空(可以将请求header打印出来, 查看headers中的contentType)。
 *
 * 如果curl没有返回200。
 *
 * 如果提示域名解析失败或者连接失败, 按照以下方式排查。
 *
 * ping <对应域名或ip>
 *
 * telnet <对应域名或ip> 443
 *
 * 连接手机热点ping和telnet，更换其它网络ping和telnet
 *
 * 关闭电脑防火墙ping和telnet
 *
 * 联系IT对存储OSS公网域名加白，域名见本文档下方附录I
 *
 * 如果是专属存储，联系专属存储运维同学，查看网络连通性问题
 *
 * 附录I 存储OSS公网域名
 * sz.trans.dingtalk.com
 *
 * sh.trans.dingtalk.com
 *
 * zjk.trans.dingtalk.com
 *
 * lippi-space-zjk.oss-cn-zhangjiakou.aliyuncs.com
 *
 * lippi-space-sh.oss-cn-shanghai.aliyuncs.com
 *
 * lippi-space-sz.oss-cn-shenzhen.aliyuncs.com
 *
 * cdn-zjk-trans.dingtalk.com
 *
 * cdn-sh-trans.dingtalk.com
 *
 * cdn-sz-trans.dingtalk.com
 *
 * 不知道上传到哪去了。
 *
 * @Title: OSSTest
 * @Description: OSSTest
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-04-10
 * @version: V1.0
 */
public class OSSTest {

    private static String resourceUrl = "";

    private static Map<String, String> headers;

    private static String uploadKey = "";

    public static void main(String[] args) throws IOException {

        one();
        uposs();
        commit();
    }

    public static void one() {

        String json = "{\n"
            + "  \"protocol\" : \"HEADER_SIGNATURE\",\n"
            + "  \"multipart\" : false,\n"
            + "  \"option\" : {\n"
            + "    \"storageDriver\" : \"DINGTALK\",\n"
            + "    \"preCheckParam\" : {\n"
            + "      \"parentId\" : \"0\"\n"
            + "    },\n"
            + "    \"preferIntranet\" : true\n"
            + "  }\n"
            + "}";
        String url = "https://api.dingtalk.com/v1.0/storage/spaces/21376823510/files/uploadInfos/query?unionId=" + DingUploadTest.properties.get("unionid");
        String result2 = HttpRequest.post(url)
            .header("x-acs-dingtalk-access-token", token)
            .body(json)
            .execute().body();

        HashMap hashMap = new Gson().fromJson(result2, HashMap.class);
        LinkedTreeMap headerSignatureInfo = (LinkedTreeMap) hashMap.get("headerSignatureInfo");
        headers = (LinkedTreeMap) headerSignatureInfo.get("headers");
        List<String> resourceUrls = (List) headerSignatureInfo.get("resourceUrls");
        resourceUrl = resourceUrls.get(0);
        uploadKey = (String) hashMap.get("uploadKey");
        Console.log(result2);
    }

    public static void uposs() throws IOException {

        // 从接口返回信息中拿到resourceUrls
        // 从接口返回信息中拿到headers

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
        InputStream is = new FileInputStream("G:\\ideaIU-2023.1.win\\product-info.json");
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

    public static void commit() {

        String json = "{\n"
            + "  \"uploadKey\" : \"" + uploadKey + "\",\n"
            + "  \"name\" : \"product-info.json\",\n"
            + "  \"parentId\" : \"0\"\n"
            + "}";
        String url = "https://api.dingtalk.com/v1.0/storage/spaces/21376823510/files/commit?unionId=" + DingUploadTest.properties.get("unionid");
        String result2 = HttpRequest.post(url)
            .header("x-acs-dingtalk-access-token", token)
            .body(json)
            .execute().body();
        Console.log(result2);
    }
}
