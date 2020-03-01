package com.appleyk.common;

import com.appleyk.dict.TBusinessCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>响应结果</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:40 2020/2/29
 */
public class TResult implements Serializable {

    private static final long serialVersionUID = 2719931935414658118L;

    private  Integer status;

    private  String message;

    @JsonInclude(value = Include.NON_NULL)
    private  Object data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private  Date timeStamp = new Date();

    public TResult(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public TResult(Integer status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功后直接调用这个
     * @param data
     */
    private TResult(Object data){
        super();
        this.status = TBusinessCode.OK.getCode();
        this.message = TBusinessCode.OK.getDes();
        this.data = data;
    }

    public static TResult ok(Object data){
        return new TResult(data);
    }

    public TResult() {
        super();
        this.status = null;
        this.message = null;
        this.data = null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

}
