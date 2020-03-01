package com.appleyk.config;

import com.appleyk.common.TLoggerHelper;
import com.appleyk.common.TResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>全局异常拦截</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 20:34 2020/3/1
 */
@CrossOrigin
@RestControllerAdvice
public class TlobalExceptionHandler {

    @ExceptionHandler
    public TResult processException(Exception ex) {

        TLoggerHelper.error(ex.toString());
        return new TResult(500, ex.getMessage());

    }

}
