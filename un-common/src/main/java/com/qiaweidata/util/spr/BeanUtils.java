package com.qiaweidata.util.spr;

import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanUtils
{
    /**
     * 将map装换为javabean对象
     * 
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean)
    {
        BeanMap beanMap = BeanMap.create(bean);
        for (String key : map.keySet())
        {
            beanMap.put(key.toLowerCase(), map.get(key)); // 转换成小写处理，否则无法设置属性
        }
        return bean;
    }
    
    /**
     * 将List<Map<String,Object>>转换为List<T>
     * 
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
        throws InstantiationException, IllegalAccessException
    {
        List<T> list = new ArrayList<T>();
        if (maps != null && maps.size() > 0)
        {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++)
            {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }
}
