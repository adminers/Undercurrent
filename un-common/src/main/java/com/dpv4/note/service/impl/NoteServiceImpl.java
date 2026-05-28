package com.dpv4.note.service.impl;

import com.dpv4.common.util.PageUtil;
import com.dpv4.common.util.StringUtil;
import com.dpv4.course.dto.PageResponse;
import com.dpv4.note.model.Note;
import com.dpv4.note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void initData() {
        String[] sampleNotes = {
            "Git 常用命令#Git：git add -A, git commit, git push, git pull, git status, git log",
            "Java 8 Stream API#Java：map, filter, reduce, collect, forEach 等操作非常强大",
            "Spring Boot 自动配置#Spring：@EnableAutoConfiguration, @Conditional 系列注解",
            "前端性能优化#前端：懒加载、CDN、缓存、压缩、Tree Shaking、代码分割",
            "SQL 优化技巧#数据库：索引优化、慢查询分析、执行计划、分页优化",
            "设计模式总结#架构：单例、工厂、策略、观察者、装饰器、适配器模式",
            "Vue 3 组合式 API#Vue：setup, ref, reactive, computed, watch, onMounted",
            "React Hooks 最佳实践#React：useState, useEffect, useMemo, useCallback, useRef",
            "Docker 常用命令#DevOps：docker build, run, ps, logs, exec, compose",
            "Linux 常用命令#Linux：cd, ls, grep, awk, sed, find, ssh, scp",
            "算法学习笔记#算法：时间复杂度、空间复杂度、排序、查找、动态规划",
            "每日学习计划#学习：保持每天学习 2 小时，记录笔记和总结",
            "项目注意事项#项目：代码规范、单元测试、文档、Code Review",
            "待办事项#待办：完成用户管理模块、优化首页加载、修复登录问题"
        };

        for (String note : sampleNotes) {
            String[] parts = note.split("#", 2);
            String title = parts[0];
            String content = parts.length > 1 ? parts[1] : "";
            String category = extractCategory(content);
            
            notes.add(Note.builder()
                .id(idGenerator.getAndIncrement())
                .title(title)
                .content(content)
                .category(category)
                .pinned(notes.size() < 3)
                .viewCount((int) (Math.random() * 100))
                .createTime(LocalDateTime.now().minusDays(sampleNotes.length - notes.size()))
                .updateTime(LocalDateTime.now().minusDays(sampleNotes.length - notes.size()))
                .build());
        }
    }

    private String extractCategory(String content) {
        if (content.contains("Git")) return "Git";
        if (content.contains("Java")) return "Java";
        if (content.contains("Spring")) return "Spring";
        if (content.contains("前端")) return "前端";
        if (content.contains("SQL") || content.contains("数据库")) return "数据库";
        if (content.contains("设计模式") || content.contains("架构")) return "架构";
        if (content.contains("Vue") || content.contains("React")) return "前端";
        if (content.contains("Docker") || content.contains("Linux")) return "DevOps";
        if (content.contains("算法")) return "算法";
        if (content.contains("学习")) return "学习";
        if (content.contains("待办")) return "待办";
        return "其他";
    }

    @Override
    public List<Note> getAllNotes() {
        log.info("获取所有笔记");
        return notes.stream()
            .sorted(Comparator.comparing(Note::getPinned, Comparator.reverseOrder())
                .thenComparing(Note::getCreateTime, Comparator.reverseOrder()))
            .collect(Collectors.toList());
    }

    @Override
    public PageResponse<Note> getNotesByPage(int page, int size) {
        log.info("分页获取笔记：page={}, size={}", page, size);
        List<Note> sortedNotes = notes.stream()
            .sorted(Comparator.comparing(Note::getPinned, Comparator.reverseOrder())
                .thenComparing(Note::getCreateTime, Comparator.reverseOrder()))
            .collect(Collectors.toList());
        
        int total = sortedNotes.size();
        int totalPages = PageUtil.getTotalPages(total, size);
        List<Note> pageNotes = PageUtil.getPage(sortedNotes, page, size);

        return PageResponse.<Note>builder()
            .records(pageNotes)
            .total(total)
            .page(page)
            .size(size)
            .totalPages(totalPages)
            .build();
    }

    @Override
    public Note getNoteById(Long id) {
        log.info("获取笔记详情：id={}", id);
        return notes.stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Note createNote(String title, String content, String category) {
        log.info("创建笔记：title={}, category={}", title, category);
        Note note = Note.builder()
            .id(idGenerator.getAndIncrement())
            .title(title)
            .content(content)
            .category(StringUtil.isNotBlank(category) ? category : "其他")
            .pinned(false)
            .viewCount(0)
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .build();
        notes.add(note);
        return note;
    }

    @Override
    public Note updateNote(Long id, String title, String content, String category) {
        log.info("更新笔记：id={}", id);
        Note note = getNoteById(id);
        if (note != null) {
            note.setTitle(title);
            note.setContent(content);
            if (StringUtil.isNotBlank(category)) {
                note.setCategory(category);
            }
            note.setUpdateTime(LocalDateTime.now());
        }
        return note;
    }

    @Override
    public boolean deleteNote(Long id) {
        log.info("删除笔记：id={}", id);
        return notes.removeIf(n -> n.getId().equals(id));
    }

    @Override
    public boolean togglePin(Long id) {
        log.info("切换置顶：id={}", id);
        Note note = getNoteById(id);
        if (note != null) {
            note.setPinned(!note.getPinned());
            note.setUpdateTime(LocalDateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public List<Note> getPinnedNotes() {
        log.info("获取置顶笔记");
        return notes.stream()
            .filter(Note::getPinned)
            .sorted(Comparator.comparing(Note::getCreateTime, Comparator.reverseOrder()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Note> searchNotes(String keyword) {
        log.info("搜索笔记：keyword={}", keyword);
        if (StringUtil.isBlank(keyword)) {
            return getAllNotes();
        }
        String lowerKeyword = keyword.toLowerCase();
        return notes.stream()
            .filter(n -> n.getTitle().toLowerCase().contains(lowerKeyword) ||
                n.getContent().toLowerCase().contains(lowerKeyword) ||
                n.getCategory().toLowerCase().contains(lowerKeyword))
            .sorted(Comparator.comparing(Note::getPinned, Comparator.reverseOrder())
                .thenComparing(Note::getCreateTime, Comparator.reverseOrder()))
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategories() {
        log.info("获取所有分类");
        return notes.stream()
            .map(Note::getCategory)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<Note> getNotesByCategory(String category) {
        log.info("按分类获取笔记：category={}", category);
        return notes.stream()
            .filter(n -> n.getCategory().equals(category))
            .sorted(Comparator.comparing(Note::getPinned, Comparator.reverseOrder())
                .thenComparing(Note::getCreateTime, Comparator.reverseOrder()))
            .collect(Collectors.toList());
    }

    @Override
    public boolean incrementViewCount(Long id) {
        log.info("增加笔记查看次数：id={}", id);
        Note note = getNoteById(id);
        if (note != null) {
            note.setViewCount((note.getViewCount() == null ? 0 : note.getViewCount()) + 1);
            return true;
        }
        return false;
    }
}
