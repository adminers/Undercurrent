package com.fly.test.restful;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataEntity
{
    private String id;
    
    private String name;
}
