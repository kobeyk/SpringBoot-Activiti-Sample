package com.appleyk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * <p>用户</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:37 2020/2/29
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TUser extends TObject{

    /**昵称*/
    private String alias;
    /**头像*/
    private String avatar;
    /**角色（权限在角色里）*/
    private TRole role;

    public TUser(){

    }

    public TUser(Long id , String name){
      super(id,name);
    }

    public TUser(Long id ,String name ,TRole role){
        super(id , name);
        this.role = role;
    }

    public TUser(Long id ,String name , String alias , String avatar){
        super(id , name);
        this.alias = alias ;
        this.avatar = avatar ;
    }

    @Override
    public String toString(){
        return "id = " + getId()+",name = "+getName();
    }

}
