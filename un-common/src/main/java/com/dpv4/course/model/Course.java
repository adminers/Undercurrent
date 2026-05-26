package com.dpv4.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    
    private List<String> tags;
    
    private Integer sort;
    
    private Integer status;
    
    private Integer viewCount;
    
    private Integer favoriteCount;
    
    private Double rating;
    
    private Integer ratingCount;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
}