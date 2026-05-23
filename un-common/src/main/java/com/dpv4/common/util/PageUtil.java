package com.dpv4.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageUtil {

    private PageUtil() {
    }

    public static <T> List<T> getPage(List<T> list, int page, int size) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        int total = list.size();
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, total);

        if (fromIndex >= total) {
            return new ArrayList<>();
        }

        return new ArrayList<>(list.subList(fromIndex, toIndex));
    }

    public static int getTotalPages(int total, int size) {
        if (size <= 0) {
            return 0;
        }
        return (int) Math.ceil((double) total / size);
    }

    public static boolean isValidPage(int page, int totalPages) {
        return page >= 1 && page <= totalPages;
    }

    public static List<Integer> getVisiblePages(int currentPage, int totalPages, int maxVisible) {
        List<Integer> pages = new ArrayList<>();

        if (totalPages <= 0) {
            return pages;
        }

        int half = maxVisible / 2;
        int start = Math.max(1, currentPage - half);
        int end = Math.min(totalPages, start + maxVisible - 1);

        if (end - start < maxVisible - 1) {
            start = Math.max(1, end - maxVisible + 1);
        }

        for (int i = start; i <= end; i++) {
            pages.add(i);
        }

        return pages;
    }
}
