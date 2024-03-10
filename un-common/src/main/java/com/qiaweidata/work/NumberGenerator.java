package com.qiaweidata.work;

public class NumberGenerator {
    public static void main(String[] args) {
        int start = 100;
        int step = 100;
        int end = 100000;
        
        for (int i = start; i <= end; i += step) {
            System.out.println("INSERT INTO ekb_page_list (start_page) VALUES(" + i + ");");
        }
    }
}
