package com.fly.quick.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fly.core.JsonResult;
import com.fly.core.exception.ValidateException;
import com.fly.core.utils.Executor;
import com.fly.core.utils.XmlUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "组件源码接口")
@RestController
@RequestMapping("/depend")
public class DependController
{
    private String mvnRepoPath;
    
    @PostConstruct
    public void init()
    {
        try
        {
            String mavenHome = Executor.execute("mvn -v")
                .stream()
                .filter(r -> r.startsWith("Maven home:"))
                .map(arg -> StringUtils.substringAfter(arg, "Maven home:"))
                .collect(Collectors.joining());
            String settings = mavenHome.trim() + "/conf/settings.xml";
            log.info("settings Path: {}", settings);
            String xmlContent = FileUtils.readFileToString(new File(settings), StandardCharsets.UTF_8.toString());
            Properties prop = XmlUtils.xmlToProperties(xmlContent);
            mvnRepoPath = prop.getProperty("localRepository");
            if (StringUtils.isBlank(mvnRepoPath))
            {
                mvnRepoPath = System.getProperty("user.home") + "/.m2/repository";
            }
            log.info("mvnRepo Path: {}", mvnRepoPath);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    /**
     * 测试文件请选择：src\main\resources\test\pomx.ml<br>
     * maven本地仓库地址请在application.yml文件中配置mvnRepoPath值
     * 
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "1. pom文件提交")
    @PostMapping(value = "/pom", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JsonResult<?> pom(MultipartFile file)
        throws IOException
    {
        if (file == null || !file.getOriginalFilename().endsWith(".xml"))
        {
            throw new ValidateException("文件为空或文件格式不是xml");
        }
        File rootDir = new File("upload");
        File dest = new File(rootDir.getCanonicalPath() + "/" + file.getOriginalFilename());
        dest.getParentFile().mkdirs();
        file.transferTo(dest.getAbsoluteFile());
        
        // 运行package命令，收集jar到lib
        String pomPath = dest.getAbsolutePath();
        log.info("pom.xml 文件路径：{}", pomPath);
        String command = "mvn clean package -f " + pomPath;
        Executor.execute(command).forEach(log::info);
        
        // 收集sources
        String srcCommand = "mvn dependency:sources -DdownloadSources=true -f " + pomPath;
        List<String> listData = Executor.execute(srcCommand).stream().filter(d -> StringUtils.contains(d, ":jar:sources:")).collect(Collectors.toList());
        copyResource(listData, mvnRepoPath);
        return JsonResult.success(dest.getName());
    }
    
    private void copyResource(List<String> listData, String mvnRepoPath)
        throws IOException
    {
        File directory = new File("upload//target//sources");
        StopWatch watch = new StopWatch();
        watch.start();
        listData.forEach(item -> {
            if (item.contains(":jar:sources:"))
            {
                String[] array = StringUtils.substringAfterLast(item, " ").split(":");
                String groupId, artifactId, version, path;
                if (array.length >= 5)
                {
                    try
                    {
                        groupId = array[0];
                        artifactId = array[1];
                        version = array[4];
                        path = String.format("%s/%s/%s/%s/%s-%s-sources.jar", mvnRepoPath, groupId.replace(".", "/"), artifactId, version, artifactId, version);
                        log.info("------ path: {}   ", path);
                        FileUtils.copyFileToDirectory(new File(path), directory);
                    }
                    catch (IOException e)
                    {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        });
        watch.stop();
        log.info("Run cost time ms: {}", watch.getTime(TimeUnit.MILLISECONDS));
        if (SystemUtils.IS_OS_WINDOWS)
        {
            Runtime.getRuntime().exec("cmd /c start " + directory.getParentFile().getPath());
        }
        FileUtils.forceDeleteOnExit(directory);
    }
    
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "2. lib、源码文件下载")
    @GetMapping(value = "/down/libAndSrc", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void down(HttpServletResponse response)
        throws IOException
    {
        List<Path> filePaths = new ArrayList<>();
        Stream.of(new File("upload//target//lib").listFiles()).forEach(lib -> filePaths.add(lib.toPath()));
        Stream.of(new File("upload//target//sources").listFiles()).forEach(src -> filePaths.add(src.toPath()));
        
        // 压缩多个文件到zip文件中
        String filename = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss");
        response.setHeader("Content-Disposition", "attachment;filename=LIB_SRC_" + filename + ".zip");
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream()))
        {
            for (Path path : filePaths)
            {
                try (InputStream inputStream = Files.newInputStream(path))
                {
                    zipOutputStream.putNextEntry(new ZipEntry(path.getFileName().toString()));
                    StreamUtils.copy(inputStream, zipOutputStream);
                    zipOutputStream.flush();
                }
            }
        }
    }
}
