package com.dpv4.note.controller;

import com.dpv4.course.dto.PageResponse;
import com.dpv4.note.model.Note;
import com.dpv4.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        log.info("获取所有笔记");
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Note>> getNotesByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("分页获取笔记：page={}, size={}", page, size);
        return ResponseEntity.ok(noteService.getNotesByPage(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        log.info("获取笔记详情：id={}", id);
        Note note = noteService.getNoteById(id);
        if (note != null) {
            noteService.incrementViewCount(id);
            return ResponseEntity.ok(note);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(
            @RequestParam String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "其他") String category) {
        log.info("创建笔记：title={}", title);
        Note note = noteService.createNote(title, content, category);
        return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(required = false) String category) {
        log.info("更新笔记：id={}", id);
        Note note = noteService.updateNote(id, title, content, category);
        if (note != null) {
            return ResponseEntity.ok(note);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        log.info("删除笔记：id={}", id);
        boolean deleted = noteService.deleteNote(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/toggle-pin")
    public ResponseEntity<Void> togglePin(@PathVariable Long id) {
        log.info("切换置顶：id={}", id);
        boolean toggled = noteService.togglePin(id);
        if (toggled) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pinned")
    public ResponseEntity<List<Note>> getPinnedNotes() {
        log.info("获取置顶笔记");
        return ResponseEntity.ok(noteService.getPinnedNotes());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam String keyword) {
        log.info("搜索笔记：keyword={}", keyword);
        return ResponseEntity.ok(noteService.searchNotes(keyword));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        log.info("获取所有分类");
        return ResponseEntity.ok(noteService.getAllCategories());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Note>> getNotesByCategory(@PathVariable String category) {
        log.info("按分类获取笔记：category={}", category);
        return ResponseEntity.ok(noteService.getNotesByCategory(category));
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementViewCount(@PathVariable Long id) {
        log.info("增加查看次数：id={}", id);
        boolean success = noteService.incrementViewCount(id);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
