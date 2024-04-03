package com.fly.demo.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * IndexController
 * 
 * @author 00fly
 * @version [版本号, 2018-09-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class IndexController
{
    @Autowired
    ApplicationContext applicationContext;
    
    /**
     * 首页
     * 
     * @param model
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
        throws Exception
    {
        model.addAttribute("urls", getRequestMappingURL());
        return "index";
    }
    
    /**
     * 得到类上面的注解信息
     * 
     * @param scannerClass
     * @param allowInjectClass
     * @return
     */
    private Annotation getClassAnnotation(Class<?> scannerClass, Class<? extends Annotation> allowInjectClass)
    {
        if (!scannerClass.isAnnotationPresent(allowInjectClass))
        {
            return null;
        }
        return scannerClass.getAnnotation(allowInjectClass);
    }
    
    /**
     * 使用Java反射得到注解的信息
     * 
     * @param annotation
     * @param methodName
     * @return Exception
     */
    private Object getAnnotationInfo(Annotation annotation, String methodName)
        throws Exception
    {
        if (annotation == null)
        {
            return null;
        }
        Method declaredMethod = annotation.getClass().getDeclaredMethod(methodName, null);
        return declaredMethod.invoke(annotation, null);
    }
    
    /**
     * 从上下文获取 Controller注解类的 RequestMapping注解url信息
     * 
     * @param applicationContext
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    private List<String> getRequestMappingURL()
        throws Exception
    {
        List<String> list = new ArrayList<>();
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
        for (String key : map.keySet())
        {
            Class clazz = (Class)map.get(key).getClass();
            Annotation classAnnotation = getClassAnnotation(clazz, RequestMapping.class);
            Object object = getAnnotationInfo(classAnnotation, "value");
            if (object != null)
            {
                String[] array = (String[])object;
                list.addAll(Arrays.asList(array));
            }
        }
        return list;
    }
}
