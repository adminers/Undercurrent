package com.fly.hello.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "图片接口")
@Slf4j
@RestController
@RequestMapping("/show")
public class PicDataController
{
    Resource[] resources;
    
    List<Resource> resourceList = new ArrayList<>();
    
    /**
     * FIFO
     */
    private Queue<Integer> quque = new ConcurrentLinkedQueue<>();
    
    @PostConstruct
    private void init()
        throws IOException
    {
        resources = new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "data/pic/**/*.jpg");
        Arrays.stream(resources).forEach(image -> {
            resourceList.add(image);
            log.info("add pic: {}", image.getFilename());
        });
    }
    
    @ApiOperation("图片")
    @GetMapping(value = {"/girl", "/pic"}, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] showPic1()
        throws IOException
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(createImage(), "jpg", os);
        return os.toByteArray();
    }
    
    /**
     * createImage 生成图片
     * 
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    private synchronized BufferedImage createImage()
        throws IOException
    {
        // 保留3条记录
        while (quque.size() > 3)
        {
            quque.poll();
        }
        
        // 新生成无重复数据
        int index;
        do
        {
            index = RandomUtils.nextInt(0, resources.length);
        } while (quque.contains(index));
        quque.add(index);
        log.info("add： {}, quque:{}", index, quque);
        
        // 取图片
        return ImageIO.read(resources[index].getInputStream());
    }
}
