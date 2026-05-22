package com.dpv4.course.controller;

import com.dpv4.course.dto.PageResponse;
import com.dpv4.course.model.Course;
import com.dpv4.course.model.CourseCategory;
import com.dpv4.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/categories")
    public ResponseEntity<List<CourseCategory>> getCategories() {
        log.info("获取课程分类列表");
        List<CourseCategory> categories = courseService.getCategoryList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Course>> getCourseList(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size) {
        log.info("获取课程列表: categoryId={}, page={}, size={}", categoryId, page, size);
        PageResponse<Course> pageResponse = courseService.getCourseList(categoryId, page, size);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseDetail(@PathVariable Long id) {
        log.info("获取课程详情: id={}", id);
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }
}
