package com.dpv4.course.service;

import com.dpv4.course.dto.PageResponse;
import com.dpv4.course.model.Course;
import com.dpv4.course.model.CourseCategory;

import java.util.List;

public interface CourseService {

    List<CourseCategory> getCategoryList();

    PageResponse<Course> getCourseList(Long categoryId, int page, int size);

    Course getCourseById(Long id);
}
