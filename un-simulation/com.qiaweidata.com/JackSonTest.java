package com.fly.test.restful.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.test.restful.DataEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JackSonTest
{
    private String jsonBody = "{\"id\":\"1\",\"name\":\"00fly.online\"}";
    
    ObjectMapper mapper = new ObjectMapper();
    
    /**
     * JsonLib
     * 
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     * 
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void test1()
        throws JsonParseException, JsonMappingException, IOException
    {
        // json字符串转Java对象
        DataEntity dataEntity = (DataEntity)mapper.readValue(jsonBody, DataEntity.class);
        log.info("dataEntity: {} ", dataEntity);
        
        // Java对象转字符串
        dataEntity.setName("00fly");
        String jsonStr = mapper.writeValueAsString(dataEntity);
        log.info("jsonStr: {} ", jsonStr);
        
        // Java List对象转字符串
        List<DataEntity> list = new ArrayList<DataEntity>();
        list.add(dataEntity);
        list.add(new DataEntity().setId("2").setName("jack"));
        jsonStr = mapper.writeValueAsString(list);
        log.info("jsonStr: {} ", jsonStr);
        
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, DataEntity.class);
        List<DataEntity> dataEntitys = mapper.readValue(jsonStr, javaType);
        log.info("dataEntitys: {} ", dataEntitys);
    }
}
