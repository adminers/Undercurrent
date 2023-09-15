package com.qiaweidata.undercurrent.web.controller;

import cn.hutool.core.io.FileUtil;
import com.qiaweidata.undercurrent.SpringUtils;
import com.qiaweidata.undercurrent.ai.ImitateCode;
import com.qiaweidata.undercurrent.job.TaskRun;
import com.qiaweidata.undercurrent.mail.MailUtils;
import com.qiaweidata.undercurrent.server.NettyServerHandler;
import com.qiaweidata.undercurrent.web.dao.PersonMapper;
import com.qiaweidata.undercurrent.web.entity.Machine;
import com.qiaweidata.undercurrent.youdao.FanyiV3Demo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@EnableTransactionManagement  // 需要事务的时候加上
@RestController
@CrossOrigin
public class PersonController {

    private static final Logger log = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonMapper personMapper;

    private NettyServerHandler channelHandler = SpringUtils.getBean(NettyServerHandler.class);

    private TaskRun taskRun = SpringUtils.getBean(TaskRun.class);

    @RequestMapping("/save")
    public String save() {

        Machine machine = new Machine();
        machine.setId(UUID.randomUUID().toString().replace("-", ""));
        machine.setSystemName("补补补补");
        machine.setParentId("");
        machine.setIconPath("");
        machine.setCreateTime(new Date());
        machine.setCreateUser("");
        machine.setUpdateTime(new Date());
        machine.setUpdateUser("");
        personMapper.insert(machine);
        return machine.getId();
    }

    @RequestMapping("/uploadFile")
    public Result<?> uploadFile(HttpServletRequest request, HttpServletResponse response) {

        String filePath = request.getParameter("filePath");
        String fileId = request.getParameter("fileId");
        String suffix = request.getParameter("suffix");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();
            try {
                file.transferTo(new File("F:\\temp\\" + fileId + suffix));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.OK("上传成功!");
    }

    @RequestMapping("/sendClietMsg")
    public Result<?> sendClietMsg(HttpServletRequest request, HttpServletResponse response) {

        String data = request.getParameter("data");
        channelHandler.sendMessage("shen:" + data);
        return Result.OK("发送成功!");
    }

    @RequestMapping(value = "/caton", method = RequestMethod.GET) @ResponseBody
    public String caton(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println(" caton");
        //TimeUnit.SECONDS.sleep(1);
        return "{\"caton\":\"caton\"}";
    }

    @RequestMapping(value = "/appendFile", method = RequestMethod.GET)
    @CrossOrigin
    public Result<?> appendFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fileName = request.getParameter("fileName");
        String data = request.getParameter("data");

        List<String> strings = FileUtil.readUtf8Lines("E:\\giteeWork\\UndercurrentPro\\effict-side\\project\\un-simulation\\FileName.txt");

        // 追加文件
        File file = new File("E:\\giteeWork\\UndercurrentPro\\effict-side\\project\\un-simulation\\" + strings.get(0) + ".java");
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println(data);
        FileUtil.appendUtf8String(data + "\n", file);
        return Result.OK("追加成功!");
    }


    @RequestMapping(value = "/createFile", method = RequestMethod.GET)
    @CrossOrigin
    public synchronized Result<?> createFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fileName = request.getParameter("fileName");

        // 追加文件
        File file = new File("E:\\giteeWork\\UndercurrentPro\\effict-side\\project\\un-simulation\\" + fileName + ".java");
        if (!file.exists()) {
            file.createNewFile();
        }
        return Result.OK("文件创建成功!");
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @CrossOrigin
    public  Result<?> query(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer a = 0;

        ImitateCode imitateCode = taskRun.getImitateCode();
        MailUtils.send("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "</head>\n" + "<body>\n" + "<div style=\"text-align: left;margin-bottom:30px\">Dear Customer,</div>\n" + "\n" + "<div style=\"text-align: left;margin-bottom:30px\">Please be informed that we are having a connection issue under the below location, customers under the location may be not able to access internet. Our engineers are performing diagnoses and we will get the problem fixed and get the service back online as soon as possible.\n" + "<br/><br/>\n" + "We will have further update once available and thank you for the patient.</div>\n" + "\n" + "<div style=\"text-align: left;margin-bottom:10px\">Outage Time: {0}</div>\n" + "<div style=\"text-align: left;margin-bottom:10px\">Message ID: {1}</div>\n" + "<div style=\"text-align: left;margin-bottom:10px\">Status: {2}</div>\n" + "\n" + "<div style=\"text-align: left; \">\n" + "<table border=1 cellPadding=0 cellSpacing=0 width=\"700px\" style=\"text-indent:5px;margin-bottom:20px;border-color:black;\">{3}</table>\n" + "</div>\n" + "<div style=\"text-align: left;margin-top:30px;margin-bottom:15px\">Best Regards,</div>\n" + "<div style=\"text-align: left;\">IT Shenzhen Support</div>\n" + "</body>\n" + "</html>\n");
        return Result.OK("Line" + taskRun.getCurrentLineIndex() + "\n" + imitateCode.getText() + "\n" + imitateCode.getCode());
    }

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    @CrossOrigin
    public  Result<?> translate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String data = request.getParameter("data");
        if (null == data || data.length() == 0) {
            return Result.error("空");
        }
        return Result.OK(FanyiV3Demo.getEnglish(data));
    }

    @RequestMapping(value = "/appendFileText", method = RequestMethod.GET)
    @CrossOrigin
    public  Result<?> appendFileText(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String data = request.getParameter("data");
        File file = new File(ImitateCode.properties.get("fileTrain"));
        int totalLines = FileUtil.getTotalLines(file);
        // taskRun.WEB_RUN.add(totalLines);
        FileUtil.appendUtf8String("\n" + data, file);
        log.info("line: {} data {}", totalLines, data);
        return Result.OK("·· append file run ··");
    }
}
