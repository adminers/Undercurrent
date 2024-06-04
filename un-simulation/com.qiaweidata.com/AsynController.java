package com.fly.hello.web;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "异步接口")
@RestController
@RequestMapping("/async")
public class AsynController
{
    @ApiOperation("Callable")
    @GetMapping("/response-body")
    public Callable<String> callable()
    {
        return () -> {
            TimeUnit.MICROSECONDS.sleep(2000);
            return "Callable result: " + RandomStringUtils.randomAlphabetic(10);
        };
    }
    
    @ApiOperation("ResponseBodyEmitter")
    @GetMapping("/responseBodyEmitter")
    public ResponseBodyEmitter responseBodyEmitter()
    {
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();
        Executors.newSingleThreadExecutor().submit(() -> {
            try
            {
                responseBodyEmitter.send("demo");
                TimeUnit.MICROSECONDS.sleep(2000);
                responseBodyEmitter.send("\ntest1:" + RandomStringUtils.randomAlphabetic(10));
                TimeUnit.MICROSECONDS.sleep(2000);
                responseBodyEmitter.send("\ntest2:" + RandomStringUtils.randomAlphabetic(10));
                responseBodyEmitter.complete();
            }
            catch (Exception ignore)
            {
            }
        });
        return responseBodyEmitter;
    }
    
    Resource resource = new ClassPathResource("data/pic/18.jpg");
    
    @ApiOperation("StreamingResponseBody")
    @GetMapping(value = "/streamBody", produces = MediaType.IMAGE_JPEG_VALUE)
    public StreamingResponseBody streamBody()
    {
        return (output) -> {
            output.write(IOUtils.toByteArray(resource.getInputStream()));
        };
    }
}
