package com.fly.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoApplicationTests
{
    private List<String> list = Arrays.asList("MY", "name", "IS", "UBer", "anD", "uc");
    
    private List<String> lowerList;
    
    /**
     * 排序 lambda 实现
     */
    @Test
    public void sort()
    {
        Collections.sort(list, (a, b) -> (a.charAt(0) < b.charAt(0) ? -1 : 1));
        list.forEach(log::info);
        list.stream().forEach(log::info);
    }
    
    @Test
    public void test()
    {
        lowerList = new ArrayList<>();
        for (String it : list)
        {
            lowerList.add(it.toLowerCase());
        }
        System.out.println(lowerList);
    }
    
    @Test
    public void test1()
    {
        lowerList = list.stream().map(name -> {
            return name.toLowerCase();
        }).collect(Collectors.toList());
        System.out.println(lowerList);
    }
    
    @Test
    public void test2()
    {
        lowerList = list.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
        lowerList.forEach(System.out::println);
    }
    
    @Test
    public void test3()
    {
        lowerList = list.stream().map(String::toLowerCase).collect(Collectors.toList());
        lowerList.forEach(System.out::println);
        
        list.stream().map(String::toLowerCase).collect(Collectors.toList()).forEach(System.out::println);
    }
}
