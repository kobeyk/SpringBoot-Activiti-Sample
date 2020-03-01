package com.appleyk.utils;

import java.util.*;

/**
 * <p>检测对象的值是否为空工具类</p>
 *
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 下午 3:29 2018-11-14
 * @Version V.1.0.1
 */
public class TCheckValueUtils {

    /**
     * 验证对象是否为空 ==> 本身空 或者 值为空
     * @param object 对象
     * @return boolean
     */
    public static boolean isNotEmpty(Object object){

        if(object == null){
            return false;
        }

        if (object instanceof Integer){
            return Integer.valueOf(object.toString()) > 0 ;
        }else if(object instanceof Long){
            return Long.valueOf(object.toString()) > 0 ;
        }else  if(object instanceof String){
            return ((String) object).trim().length() > 0;
        }else if(object instanceof StringBuffer){
            return ((StringBuffer) object).toString().trim().length() > 0;
        }else if(object instanceof Boolean){
            return Boolean.valueOf(object.toString());
        }else if(object instanceof List){
            return ((List<?>) object).size() > 0;
        }else if(object instanceof Set){
            return ((Set<?>) object).size() > 0;
        }else if(object instanceof Map){
            return ((Map<?, ?>) object).size() > 0;
        }else if(object instanceof Iterator){
            return ((Iterator<?>) object).hasNext();
        }else if(object.getClass().isArray()){
            return Arrays.asList(object).size() > 0;
        }

        return true;
    }


    /**
     * 验证对象是否为空 ==> 本身空 或者 值为空
     * @param object 对象
     * @return boolean
     */
    public static boolean isEmpty(Object object){

        if(object == null){
            return true;
        }

        if (object instanceof Integer){
            return Integer.valueOf(object.toString()) == 0 ;
        }else if(object instanceof Long){
            return Long.valueOf(object.toString()) == 0 ;
        }else  if(object instanceof String){
            return ((String) object).trim().length() == 0;
        }else if(object instanceof StringBuffer){
            return ((StringBuffer) object).toString().trim().length() == 0;
        }else if(object instanceof Boolean){
            return Boolean.valueOf(object.toString());
        }else if(object instanceof List){
            return ((List<?>) object).size() == 0;
        }else if(object instanceof Set){
            return ((Set<?>) object).size() == 0;
        }else if(object instanceof Map){
            return ((Map<?, ?>) object).size() == 0;
        }else if(object instanceof Iterator){
            return !((Iterator<?>) object).hasNext();
        }else if(object.getClass().isArray()){
            return Arrays.asList(object).size() == 0;
        }

        return false;
    }


}
