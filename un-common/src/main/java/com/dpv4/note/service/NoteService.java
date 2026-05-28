package com.dpv4.note.service;

import com.dpv4.course.dto.PageResponse;
import com.dpv4.note.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    PageResponse<Note> getNotesByPage(int page, int size);
    Note getNoteById(Long id);
    Note createNote(String title, String content, String category);
    Note updateNote(Long id, String title, String content, String category);
    boolean deleteNote(Long id);
    boolean togglePin(Long id);
    List<Note> getPinnedNotes();
    List<Note> searchNotes(String keyword);
    List<String> getAllCategories();
    List<Note> getNotesByCategory(String category);
    boolean incrementViewCount(Long id);
}
