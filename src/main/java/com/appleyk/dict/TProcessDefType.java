package com.appleyk.dict;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * <p>流程定义类型（对应bpmn文件的process名称）</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:08 2020/3/1
 */
public enum TProcessDefType {

    TAKE_LEAVE("take_leave","请假流程");

    private final String name;
    private final String des;

    TProcessDefType(String name,String des){
        this.name = name;
        this.des = des;
    }

    public static TProcessDefType getProcDefType(String name){
        for (TProcessDefType type : TProcessDefType.values()) {
            if(type.getName().equals(name)){
                return type;
            }
        }
        return null;
    }

    @JsonCreator
    public String getDes() {
        return des;
    }

    public String getName() {
        return name;
    }
}
