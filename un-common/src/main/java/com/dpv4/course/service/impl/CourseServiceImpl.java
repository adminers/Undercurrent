package com.dpv4.course.service.impl;

import com.dpv4.course.dto.PageResponse;
import com.dpv4.course.model.Course;
import com.dpv4.course.model.CourseCategory;
import com.dpv4.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private List<CourseCategory> categories = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void initData() {
        categories = List.of(
            CourseCategory.builder()
                .id(1L)
                .name("前端开发")
                .description("前端技术课程")
                .icon("💻")
                .sort(1)
                .status(1)
                .createTime(LocalDateTime.now())
                .build(),
            CourseCategory.builder()
                .id(2L)
                .name("后端开发")
                .description("后端技术课程")
                .icon("🔧")
                .sort(2)
                .status(1)
                .createTime(LocalDateTime.now())
                .build(),
            CourseCategory.builder()
                .id(3L)
                .name("移动开发")
                .description("移动应用开发")
                .icon("📱")
                .sort(3)
                .status(1)
                .createTime(LocalDateTime.now())
                .build(),
            CourseCategory.builder()
                .id(4L)
                .name("人工智能")
                .description("AI与机器学习")
                .icon("🤖")
                .sort(4)
                .status(1)
                .createTime(LocalDateTime.now())
                .build(),
            CourseCategory.builder()
                .id(5L)
                .name("设计创意")
                .description("UI/UX设计课程")
                .icon("🎨")
                .sort(5)
                .status(1)
                .createTime(LocalDateTime.now())
                .build(),
            CourseCategory.builder()
                .id(6L)
                .name("产品运营")
                .description("产品与运营课程")
                .icon("📊")
                .sort(6)
                .status(1)
                .createTime(LocalDateTime.now())
                .build()
        );

        String[] titles = {
            "Vue.js 3.0 从入门到精通",
            "React Hooks 实战课程",
            "TypeScript 系统学习",
            "微信小程序开发实战",
            "Spring Boot 3.0 深度解析",
            "Go语言高级编程",
            "微服务架构实战",
            "Docker与Kubernetes",
            "Flutter 跨平台开发",
            "React Native 实战",
            "SwiftUI 从零到精通",
            "Android 开发进阶",
            "机器学习入门到实战",
            "深度学习实战指南",
            "自然语言处理实战",
            "计算机视觉实战",
            "UI设计入门到精通",
            "Figma 高级实战",
            "PS实战技巧课程",
            "AE动效设计入门",
            "产品思维训练",
            "用户增长实战",
            "数据分析实战",
            "内容运营入门"
        };

        String[] descriptions = {
            "系统学习Vue3的Composition API、响应式原理、生态工具",
            "深入掌握React Hooks，打造高质量的React应用",
            "从基础到高级，全面掌握TypeScript类型系统",
            "从零开始开发微信小程序，掌握小程序核心技能",
            "深入Spring Boot3，学习企业级应用开发最佳实践",
            "精通Go语言核心特性，构建高性能应用",
            "学习微服务架构设计，掌握服务治理核心技术",
            "从容器化到容器编排，掌握云原生开发技术",
            "用Flutter快速构建iOS/Android跨平台应用",
            "React Native原生模块开发与性能优化",
            "SwiftUI构建现代iOS应用，掌握声明式UI",
            "Android Jetpack组件与架构最佳实践",
            "机器学习算法详解，实战项目带你入门",
            "神经网络与深度学习实战，打造AI应用",
            "NLP核心技术，实战文本分类、情感分析",
            "CV基础与实战，目标检测、图像识别",
            "UI设计基础到进阶，掌握现代设计方法",
            "Figma高效工作流，团队协作与组件设计",
            "Photoshop实用技巧，后期处理与特效",
            "After Effects动效设计，打造流畅动画",
            "产品思维训练，从需求到产品的完整流程",
            "用户增长实战，AARRR模型与增长黑客",
            "数据分析方法与工具，数据驱动决策",
            "内容运营从入门到精通，爆款内容打造"
        };

        String[] colors = {
            "#667eea", "#764ba2", "#f093fb", "#f5576c",
            "#4facfe", "#00f2fe", "#43e97b", "#38f9d7",
            "#fa709a", "#fee140", "#a18cd1", "#fbc2eb",
            "#ff9a9e", "#fecfef", "#ffecd2", "#fcb69f",
            "#a8edea", "#fed6e3", "#ff8a00", "#e52e71",
            "#6a11cb", "#2575fc", "#009efd", "#2af598"
        };

        for (int i = 0; i < 24; i++) {
            long categoryId = (i % 6) + 1;
            String categoryName = categories.get((int) (categoryId - 1)).getName();
            courses.add(Course.builder()
                .id((long) (i + 1))
                .title(titles[i])
                .description(descriptions[i])
                .thumbnail("https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=" + 
                    java.net.URLEncoder.encode(categoryName + " course abstract colorful") + "&image_size=square")
                .categoryId(categoryId)
                .categoryName(categoryName)
                .sort(i + 1)
                .status(1)
                .createTime(LocalDateTime.now().minusDays(24 - i))
                .build());
        }
    }

    @Override
    public List<CourseCategory> getCategoryList() {
        log.info("获取课程分类列表");
        return categories.stream()
                .filter(c -> c.getStatus() == 1)
                .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<Course> getCourseList(Long categoryId, int page, int size) {
        log.info("获取课程列表: categoryId={}, page={}, size={}", categoryId, page, size);

        List<Course> filteredCourses;
        if (categoryId != null && categoryId > 0) {
            filteredCourses = courses.stream()
                    .filter(c -> c.getCategoryId().equals(categoryId))
                    .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                    .collect(Collectors.toList());
        } else {
            filteredCourses = courses.stream()
                    .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                    .collect(Collectors.toList());
        }

        int total = filteredCourses.size();
        int totalPages = (int) Math.ceil((double) total / size);
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, total);

        List<Course> pageCourses;
        if (fromIndex < total) {
            pageCourses = filteredCourses.subList(fromIndex, toIndex);
        } else {
            pageCourses = new ArrayList<>();
        }

        return PageResponse.<Course>builder()
                .records(pageCourses)
                .total(total)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .build();
    }

    @Override
    public Course getCourseById(Long id) {
        log.info("获取课程详情: id={}", id);
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
