package com.appleyk.common;

import com.appleyk.dict.TBusinessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>日志工具类</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:40 2020/2/29
 */
public class TLoggerHelper {
    private static Logger gxLogger = LoggerFactory.getLogger(TLoggerHelper.class);
    public static void info(String message){
        gxLogger.info(message);
    }
    public static void debug(String message){
        gxLogger.debug(message);
    }
    public static void error(String message,Exception ex){
        gxLogger.error(message,ex);
    }
    public static void error(Integer errCode,String message){
        gxLogger.error("错误码："+errCode+"，错误消息："+message);
    }
    public static void error(String message){
        gxLogger.error("错误消息："+message);
    }
    public static void error(TBusinessCode code, Exception ex){
        gxLogger.error("业务码："+code.getCode()+"，业务消息："+code.getDes()+",异常信息："+ex.getMessage());
    }

}
