package com.qiaweidata.undercurrent.web.controller;

import com.qiaweidata.undercurrent.web.dao.PersonMapper;
import com.qiaweidata.undercurrent.web.entity.Machine;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@EnableTransactionManagement  // 需要事务的时候加上
@RestController
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonMapper personMapper;

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


}
