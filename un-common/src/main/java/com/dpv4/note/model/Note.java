package com.dpv4.note.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private Long id;
    private String title;
    private String content;
    private String category;
    private Boolean pinned;
    private Integer viewCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
