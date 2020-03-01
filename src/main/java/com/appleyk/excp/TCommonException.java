package com.appleyk.excp;
import com.appleyk.dict.TBusinessCode;
import com.appleyk.common.TLoggerHelper;
import lombok.Data;

/**
 * <p>业务系统通用异常类</p>
 *
 * @author yukun24@126.com
 * @version V.1.0.1
 * @company 苏州中科蓝迪
 * @date created on 上午 9:50 2019-4-19
 */
@Data
public class TCommonException extends Exception {

    private Integer code;
    private String des;

    public TCommonException(String message){
        super(message);
    }

    public TCommonException(TBusinessCode code,String message){
        super(message);
        this.code = code.getCode();
        this.des = code.getDes();
        TLoggerHelper.error(code,this);
    }

}
