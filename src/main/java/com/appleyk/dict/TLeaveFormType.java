package com.appleyk.dict;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * <p></p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 19:27 2020/3/1
 */
public enum TLeaveFormType {

    COMPASSIONATE_LEAVE(0,"事假"),
    MARRIAGE_LEAVE(1,"婚假"),
    NNUAL_LEAVE(2,"年假"),
    paternity_leave(3,"陪产假");

    private final Integer code;
    private final String name;

    TLeaveFormType(Integer code,String name){
        this.code = code;
        this.name = name;
    }

    public static TLeaveFormType getLeaveType(Integer code){
        for (TLeaveFormType type : TLeaveFormType.values()) {
            if(type.getCode().equals(code)){
                return type;
            }
        }
        return null;
    }

    @JsonCreator
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
