package com.fly.core.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

/**
 * 
 * 简单缓存实现
 * 
 * @author 00fly
 * @version [版本号, 2022年3月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SimpleCacheManager
{
    private static Map<String, Object> simpleCache = new ConcurrentHashMap<>();
    
    /**
     * 设置一个缓存并带过期时间
     *
     * @param key 缓存key
     * @param value 缓存value
     * @param expired 过期时间，单位为秒
     */
    public static void set(String key, Object value, long expired)
    {
        CacheObject cacheObject = new CacheObject(key, value, System.currentTimeMillis() / 1000 + expired);
        simpleCache.put(key, cacheObject);
    }
    
    /**
     * 读取一个缓存
     *
     * @param key 缓存key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key)
    {
        CacheObject cacheObject = (CacheObject)simpleCache.get(key);
        if (null != cacheObject)
        {
            long current = System.currentTimeMillis() / 1000;
            // 未过期直接返回
            if (cacheObject.getExpired() > current)
            {
                Object result = cacheObject.getValue();
                return (T)result;
            }
            // 已过期直接删除
            if (current > cacheObject.getExpired())
            {
                del(key);
            }
        }
        return null;
    }
    
    /**
     * 根据key删除缓存
     *
     * @param key 缓存key
     */
    public static void del(String key)
    {
        simpleCache.remove(key);
    }
    
    @Getter
    static class CacheObject implements Serializable
    {
        private static final long serialVersionUID = -4882565718279480858L;
        
        private String key;
        
        private Object value;
        
        private long expired;
        
        public CacheObject(String key, Object value, long expired)
        {
            this.key = key;
            this.value = value;
            this.expired = expired;
        }
    }
}
