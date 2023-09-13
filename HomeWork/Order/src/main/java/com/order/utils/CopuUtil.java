package com.order.utils;

import org.springframework.beans.BeanUtils;

public class CopuUtil {
    public static <T> T copy(Object source,Class<T> clazz) {
        if(source == null){
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception a){
            a.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }
}
