package com.fly.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.fly.api.model.DataEntity;
import com.fly.api.model.FileEntity;
import com.fly.api.model.JsonResult;
import com.fly.api.model.JsonResult.SuccessWithData;
import com.fly.core.exception.ValidateException;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * RestFileUploadController
 * 
 * @author 00fly
 * @version [版本号, 2021年9月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Api(tags = "文件上传接口")
@RestController
public class RestFileUploadController
{
    /************************************************************/
    /******* 借助consumes处理同样url不同Content-Type请求 ********/
    /******* uploadFile 指定处理multipart/form-data内容 *********/
    /******* uploadJsonBody指定处理application/json内容 *********/
    /******* 因为使用同样的url处理请求,故Swagger无法展示 ********/
    /******* 但可以通过PostMan来测试 ****************************/
    /************************************************************/
    
    @ApiOperationSupport(order = 10)
    @ApiOperation("form-data文件上传")
    @JsonView(SuccessWithData.class)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JsonResult<?> uploadFile(MultipartFile file)
        throws IOException
    {
        if (file == null)
        {
            throw new ValidateException("文件不能为空");
        }
        File rootDir = new File("upload");
        File dest = new File(rootDir.getCanonicalPath() + "/" + file.getOriginalFilename());
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
        if (SystemUtils.IS_OS_WINDOWS)
        {
            Runtime.getRuntime().exec("cmd /c start " + rootDir.getCanonicalPath());
        }
        return JsonResult.success(dest.getName());
    }
    
    @ApiOperationSupport(order = 20)
    @ApiOperation("json上传")
    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResult<?> uploadJsonBody(@RequestBody DataEntity data)
    {
        return JsonResult.success(data);
    }
    
    @ApiOperationSupport(order = 30)
    @ApiOperation("文件上传2")
    // @JsonView(SuccessWithData.class) 需移除，JsonResult、DataEntity均定义了JsonView，打开会相互影响，导致返回json字符串data字段值为空
    @PostMapping(value = "/upload2")
    public JsonResult<?> upload2(MultipartFile file, DataEntity data)
    {
        if (file == null)
        {
            throw new ValidateException("文件不能为空");
        }
        return JsonResult.success(data);
    }
    
    @ApiOperationSupport(order = 40)
    @ApiOperation("文件上传21")
    @PostMapping(value = "/upload21")
    public JsonResult<?> upload21(FileEntity fileEntity)
    {
        MultipartFile file = fileEntity.getFile();
        if (file == null)
        {
            throw new ValidateException("文件不能为空");
        }
        DataEntity data = new DataEntity().setId(fileEntity.getId()).setName(fileEntity.getName());
        log.info("file:{}, data: {}", file.getOriginalFilename(), data);
        return JsonResult.success(data);
    }
    
    @ApiOperationSupport(order = 50)
    @ApiOperation("文件上传22")
    @PostMapping(value = "/upload22")
    public JsonResult<?> upload22(HttpServletRequest request)
    {
        // 演示MultipartHttpServletRequest
        if (request instanceof MultipartHttpServletRequest)
        {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            MultipartFile file = multipartRequest.getFile("file");
            Long id = NumberUtils.createLong(multipartRequest.getParameter("id"));
            String name = multipartRequest.getParameter("name");
            DataEntity data = new DataEntity().setId(id).setName(name);
            log.info("file:{}, data: {}", file.getOriginalFilename(), data);
            return JsonResult.success(data);
        }
        return JsonResult.error("not MultipartHttpServletRequest");
    }
    
    @ApiOperationSupport(order = 60)
    @ApiOperation("多文件上传")
    @PostMapping(value = "/upload3")
    public JsonResult<?> upload3(MultipartFile[] files, DataEntity data)
        throws IOException
    {
        if (files == null || files.length == 0)
        {
            throw new ValidateException("文件不能为空");
        }
        String dir = new File("upload").getCanonicalPath() + DateFormatUtils.format(System.currentTimeMillis(), "/yyyy/MM/");
        new File(dir).mkdirs();
        // 保持图片
        for (MultipartFile file : files)
        {
            File newFile = new File(dir + file.getOriginalFilename());
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
        }
        if (SystemUtils.IS_OS_WINDOWS)
        {
            Runtime.getRuntime().exec("cmd /c start " + dir);
        }
        return JsonResult.success(data);
    }
}
