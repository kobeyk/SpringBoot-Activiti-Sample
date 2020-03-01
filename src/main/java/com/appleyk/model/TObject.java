package com.appleyk.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>业务对象基类</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:37 2020/2/29
 */
@Data
public class TObject implements Serializable {

    private Long id ;
    private String name;

    public TObject(){}

    public TObject(Long id , String name){
        this.id = id ;
        this.name = name ;
    }
}
