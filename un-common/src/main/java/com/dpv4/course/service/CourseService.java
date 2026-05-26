package com.dpv4.course.service;

import com.dpv4.course.dto.PageResponse;
import com.dpv4.course.model.Course;
import com.dpv4.course.model.CourseCategory;

import java.util.List;

public interface CourseService {

    List<CourseCategory> getCategoryList();

    PageResponse<Course> getCourseList(Long categoryId, int page, int size);

    Course getCourseById(Long id);

    List<Course> getHotCourses(int limit);

    List<Course> getTopRatedCourses(int limit);

    List<Course> searchCourses(String keyword);

    List<Course> getCoursesByTag(String tag);

    boolean incrementViewCount(Long id);

    boolean incrementFavoriteCount(Long id);

    boolean rateCourse(Long id, double rating);

    List<String> getAllTags();
}
