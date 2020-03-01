package com.appleyk.model;

import com.appleyk.dict.TLeaveFormType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>请假单</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 22:41 2020/2/29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TLeaveForm extends TObject{

    /**谁请的*/
    private TUser user;
    /**请多少天*/
    private Integer days;
    /**什么原因*/
    private String reason;
    private TLeaveFormType leaveType = TLeaveFormType.COMPASSIONATE_LEAVE;
    /**审批状态（谁审批）*/
    private String status;
    /**操作时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uTime;

    public TLeaveForm(){}

    public TLeaveForm(Long id , String name , Integer days , String reason){
        super(id,name) ;
        this.days = days ;
        this.reason = reason;
    }

}
