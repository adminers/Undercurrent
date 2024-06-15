package com.fly.cache.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "实体数据对象")
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public interface Simple
    {
    }
    
    public interface All extends Simple
    {
    }
    
    @JsonView(Simple.class)
    @ApiModelProperty("用户id")
    private Long userId;
    
    @JsonView(Simple.class)
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;
    
    @JsonView(Simple.class)
    @NotNull(message = "年龄不能为空")
    @Range(min = 10, max = 60, message = "年龄必须在{min}-{max}")
    @ApiModelProperty("年龄")
    private Integer age;
    
    @JsonView(All.class)
    @ApiModelProperty("备注")
    private String desc;
    
    @JsonView(All.class)
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
