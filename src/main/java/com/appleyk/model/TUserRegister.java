package com.appleyk.model;

import lombok.Data;

/**
 * <p>用户注册</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 19:52 2020/3/1
 */
@Data
public class TUserRegister {

    private String userName;
    private String password;
    /**重复密码。验证两次密码是否一致*/
    private String rePassword;

    public TUserRegister(){}
}
