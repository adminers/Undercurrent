package com.fly.hello.web;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class MappingController
{
    // consumes 用来限制ContentType，指定处理请求的提交内容类型（Content-Type），例如application/json, text/html
    // curl -X GET -H "Accept:*/*" -H "Content-Type:application/xml" -d "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>" "http://127.0.0.1:8082/mapping/consumes"
    // curl -X GET -H "Accept:*/*" -H "Content-Type:application/json" -d "{\"foo\":\"bar\",\"fruit\":\"apple\"}" "http://127.0.0.1:8082/mapping/consumes"
    @GetMapping(path = "/mapping/consumes", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public JavaBean byConsumesJsonXml(JavaBean input)
    {
        return input;
    }
    
    // produces 用来限制Accept，指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
    // curl -X GET -H "Accept:application/json" -H "Content-Type:application/x-www-form-urlencoded" "http://127.0.0.1:8082/mapping/produces"
    // curl -X GET -H "Accept:application/xml" -H "Content-Type:application/x-www-form-urlencoded" "http://127.0.0.1:8082/mapping/produces"
    @GetMapping(path = "/mapping/produces", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public JavaBean byProducesJsonXml()
    {
        return new JavaBean();
    }
}

@Data
@XmlRootElement
class JavaBean
{
    private String foo = "bar";
    
    private String fruit = "apple";
}
