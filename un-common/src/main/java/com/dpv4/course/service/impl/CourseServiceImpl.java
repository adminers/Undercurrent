package com.dpv4.course.service.impl;

import com.dpv4.common.util.DateUtil;
import com.dpv4.common.util.MathUtil;
import com.dpv4.common.util.PageUtil;
import com.dpv4.common.util.StringUtil;
import com.dpv4.course.dto.PageResponse;
import com.dpv4.course.model.Course;
import com.dpv4.course.model.CourseCategory;
import com.dpv4.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
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
            "Node.js 全栈开发",
            "Webpack/Vite 构建优化",
            "Spring Boot 3.0 深度解析",
            "Go语言高级编程",
            "微服务架构实战",
            "Docker与Kubernetes",
            "Redis 高性能缓存",
            "MySQL 优化实战",
            "Flutter 跨平台开发",
            "React Native 实战",
            "SwiftUI 从零到精通",
            "Android 开发进阶",
            "Uni-app 多端开发",
            "HarmonyOS 应用开发",
            "机器学习入门到实战",
            "深度学习实战指南",
            "自然语言处理实战",
            "计算机视觉实战",
            "大模型应用开发",
            "Prompt 工程实践",
            "UI设计入门到精通",
            "Figma 高级实战",
            "PS实战技巧课程",
            "AE动效设计入门",
            "Sketch 设计大师课",
            "3D建模与Blender",
            "产品思维训练",
            "用户增长实战",
            "数据分析实战",
            "内容运营入门",
            "项目管理实战",
            "用户研究方法"
        };

        String[] descriptions = {
            "系统学习Vue3的Composition API、响应式原理、生态工具",
            "深入掌握React Hooks，打造高质量的React应用",
            "从基础到高级，全面掌握TypeScript类型系统",
            "从零开始开发微信小程序，掌握小程序核心技能",
            "Node.js后端开发，Express/Koa框架实战",
            "现代化前端构建工具，Vite配置与优化",
            "深入Spring Boot3，学习企业级应用开发最佳实践",
            "精通Go语言核心特性，构建高性能应用",
            "学习微服务架构设计，掌握服务治理核心技术",
            "从容器化到容器编排，掌握云原生开发技术",
            "Redis数据结构与实战，缓存设计与优化",
            "MySQL索引优化、慢查询分析、分库分表",
            "用Flutter快速构建iOS/Android跨平台应用",
            "React Native原生模块开发与性能优化",
            "SwiftUI构建现代iOS应用，掌握声明式UI",
            "Android Jetpack组件与架构最佳实践",
            "Uni-app一套代码，多端运行实战",
            "HarmonyOS应用开发，ArkTS语言入门",
            "机器学习算法详解，实战项目带你入门",
            "神经网络与深度学习实战，打造AI应用",
            "NLP核心技术，实战文本分类、情感分析",
            "CV基础与实战，目标检测、图像识别",
            "大模型API接入，RAG应用开发",
            "Prompt设计技巧，AI应用开发实践",
            "UI设计基础到进阶，掌握现代设计方法",
            "Figma高效工作流，团队协作与组件设计",
            "Photoshop实用技巧，后期处理与特效",
            "After Effects动效设计，打造流畅动画",
            "Sketch设计工具，移动端设计实战",
            "Blender 3D建模，三维可视化基础",
            "产品思维训练，从需求到产品的完整流程",
            "用户增长实战，AARRR模型与增长黑客",
            "数据分析方法与工具，数据驱动决策",
            "内容运营从入门到精通，爆款内容打造",
            "项目管理方法论，敏捷开发实践",
            "用户研究方法，用户体验设计基础"
        };

        String[] colors = {
            "#667eea", "#764ba2", "#f093fb", "#f5576c",
            "#4facfe", "#00f2fe", "#43e97b", "#38f9d7",
            "#fa709a", "#fee140", "#a18cd1", "#fbc2eb",
            "#ff9a9e", "#fecfef", "#ffecd2", "#fcb69f",
            "#a8edea", "#fed6e3", "#ff8a00", "#e52e71",
            "#6a11cb", "#2575fc", "#009efd", "#2af598"
        };

        List<List<String>> tagList = List.of(
            List.of("Vue", "前端", "入门"),
            List.of("React", "Hooks", "前端"),
            List.of("TypeScript", "类型", "前端"),
            List.of("小程序", "微信", "移动"),
            List.of("Node.js", "后端", "全栈"),
            List.of("Webpack", "Vite", "构建"),
            List.of("Spring Boot", "Java", "后端"),
            List.of("Go语言", "并发", "后端"),
            List.of("微服务", "架构", "后端"),
            List.of("Docker", "K8s", "云原生"),
            List.of("Redis", "缓存", "数据库"),
            List.of("MySQL", "SQL", "数据库"),
            List.of("Flutter", "跨平台", "移动"),
            List.of("React Native", "跨平台", "移动"),
            List.of("SwiftUI", "iOS", "移动"),
            List.of("Android", "Jetpack", "移动"),
            List.of("Uni-app", "跨平台", "小程序"),
            List.of("HarmonyOS", "华为", "移动"),
            List.of("机器学习", "AI", "算法"),
            List.of("深度学习", "神经网络", "AI"),
            List.of("NLP", "自然语言处理", "AI"),
            List.of("计算机视觉", "CV", "AI"),
            List.of("大模型", "LLM", "AI"),
            List.of("Prompt", "工程", "AI"),
            List.of("UI设计", "设计", "创意"),
            List.of("Figma", "协作", "设计"),
            List.of("Photoshop", "PS", "设计"),
            List.of("After Effects", "AE", "动效"),
            List.of("Sketch", "设计", "UI"),
            List.of("Blender", "3D", "建模"),
            List.of("产品经理", "产品", "思维"),
            List.of("用户增长", "运营", "增长"),
            List.of("数据分析", "数据", "运营"),
            List.of("内容运营", "内容", "运营"),
            List.of("项目管理", "敏捷", "管理"),
            List.of("用户研究", "UX", "设计")
        );

        for (int i = 0; i < 36; i++) {
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
                .tags(tagList.get(i))
                .sort(i + 1)
                .status(1)
                .viewCount(1000 + i * 100 + (int) (Math.random() * 500))
                .favoriteCount(50 + (int) (Math.random() * 200))
                .rating(3.5 + Math.random() * 1.5)
                .ratingCount(20 + (int) (Math.random() * 100))
                .createTime(LocalDateTime.now().minusDays(36 - i))
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

        List<Course> filteredCourses = filterCourses(categoryId);
        int total = filteredCourses.size();
        int totalPages = PageUtil.getTotalPages(total, size);
        List<Course> pageCourses = PageUtil.getPage(filteredCourses, page, size);

        return PageResponse.<Course>builder()
                .records(pageCourses)
                .total(total)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .build();
    }

    private List<Course> filterCourses(Long categoryId) {
        List<Course> result;
        if (categoryId != null && categoryId > 0) {
            result = courses.stream()
                    .filter(c -> c.getCategoryId().equals(categoryId))
                    .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                    .collect(Collectors.toList());
        } else {
            result = courses.stream()
                    .sorted((a, b) -> a.getSort().compareTo(b.getSort()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public Course getCourseById(Long id) {
        log.info("获取课程详情: id={}", id);
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> getHotCourses(int limit) {
        log.info("获取热门课程: limit={}", limit);
        return courses.stream()
                .sorted(Comparator.comparingInt(Course::getViewCount).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> searchCourses(String keyword) {
        log.info("搜索课程: keyword={}", keyword);
        if (StringUtil.isBlank(keyword)) {
            return courses.stream()
                    .sorted(Comparator.comparing(Course::getSort))
                    .collect(Collectors.toList());
        }
        
        String lowerKeyword = keyword.toLowerCase();
        return courses.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(lowerKeyword) ||
                        c.getDescription().toLowerCase().contains(lowerKeyword) ||
                        c.getCategoryName().toLowerCase().contains(lowerKeyword))
                .sorted(Comparator.comparing(Course::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public boolean incrementViewCount(Long id) {
        log.info("增加课程观看次数: id={}", id);
        Course course = getCourseById(id);
        if (course != null) {
            course.setViewCount((course.getViewCount() == null ? 0 : course.getViewCount()) + 1);
            return true;
        }
        return false;
    }

    @Override
    public List<Course> getTopRatedCourses(int limit) {
        log.info("获取高评分课程: limit={}", limit);
        return courses.stream()
                .sorted(Comparator.comparingDouble(Course::getRating).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesByTag(String tag) {
        log.info("根据标签获取课程: tag={}", tag);
        if (StringUtil.isBlank(tag)) {
            return new ArrayList<>();
        }
        String lowerTag = tag.toLowerCase();
        return courses.stream()
                .filter(c -> c.getTags() != null && 
                        c.getTags().stream().anyMatch(t -> t.toLowerCase().contains(lowerTag)))
                .sorted(Comparator.comparing(Course::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public boolean incrementFavoriteCount(Long id) {
        log.info("增加课程收藏次数: id={}", id);
        Course course = getCourseById(id);
        if (course != null) {
            course.setFavoriteCount((course.getFavoriteCount() == null ? 0 : course.getFavoriteCount()) + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean rateCourse(Long id, double rating) {
        log.info("课程评分: id={}, rating={}", id, rating);
        if (rating < 1 || rating > 5) {
            log.warn("评分必须在1-5之间");
            return false;
        }
        Course course = getCourseById(id);
        if (course != null) {
            int currentCount = course.getRatingCount() == null ? 0 : course.getRatingCount();
            double currentRating = course.getRating() == null ? 0 : course.getRating();
            double newRating = ((currentRating * currentCount) + rating) / (currentCount + 1);
            newRating = MathUtil.round(newRating, 1);
            course.setRating(newRating);
            course.setRatingCount(currentCount + 1);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllTags() {
        log.info("获取所有课程标签");
        Set<String> allTags = new LinkedHashSet<>();
        courses.forEach(c -> {
            if (c.getTags() != null) {
                allTags.addAll(c.getTags());
            }
        });
        return new ArrayList<>(allTags);
    }
}
