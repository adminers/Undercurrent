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

    @GetMapping("/hot")
    public ResponseEntity<List<Course>> getHotCourses(
            @RequestParam(defaultValue = "10") int limit) {
        log.info("获取热门课程: limit={}", limit);
        List<Course> courses = courseService.getHotCourses(limit);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Course>> getTopRatedCourses(
            @RequestParam(defaultValue = "10") int limit) {
        log.info("获取高评分课程: limit={}", limit);
        List<Course> courses = courseService.getTopRatedCourses(limit);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(
            @RequestParam String keyword) {
        log.info("搜索课程: keyword={}", keyword);
        List<Course> courses = courseService.searchCourses(keyword);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<String>> getAllTags() {
        log.info("获取所有课程标签");
        List<String> tags = courseService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Course>> getCoursesByTag(@PathVariable String tag) {
        log.info("根据标签获取课程: tag={}", tag);
        List<Course> courses = courseService.getCoursesByTag(tag);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseDetail(@PathVariable Long id) {
        log.info("获取课程详情: id={}", id);
        Course course = courseService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        courseService.incrementViewCount(id);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable Long id) {
        log.info("增加课程观看次数: id={}", id);
        boolean success = courseService.incrementViewCount(id);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Void> incrementFavoriteCount(@PathVariable Long id) {
        log.info("增加课程收藏次数: id={}", id);
        boolean success = courseService.incrementFavoriteCount(id);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Void> rateCourse(
            @PathVariable Long id,
            @RequestParam double rating) {
        log.info("课程评分: id={}, rating={}", id, rating);
        boolean success = courseService.rateCourse(id, rating);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
