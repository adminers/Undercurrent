package com.dpv4.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long id;
    
    private String title;
    
    private String description;
    
    private String thumbnail;
    
    private Long categoryId;
    
    private String categoryName;
    
    private Integer sort;
    
    private Integer status;
    
    private Integer viewCount;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
}