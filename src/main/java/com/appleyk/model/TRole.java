package com.appleyk.model;

import com.appleyk.common.TPurview;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>用户角色</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:42 2020/2/29
 */
@Data
public class TRole implements Serializable {

    /**系统超管*/
    public static final Integer SUPER_MANAGER_ROLE = 1;
    /**普通管理员*/
    public static final Integer COMMON_MANAGER_ROLE = 2;
    /**总经理*/
    public static final Integer GENERAL_MANAGER_ROLE = 3;
    /**项目经理*/
    public static final Integer PROJECT_MANAGER_ROLE = 4;
    /**人事*/
    public static final Integer HUMAN_AFFAIRS = 5;
    /**员工(默认注册进来就是普通员工)*/
    public static final Integer DEFAULT_EMPLOYEE_ROLE = 6;

    /**角色ID*/
    private Integer rid;

    /**角色名称*/
    private String name;

    /**权限值*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long purview;

    /**默认角色、默认权限*/
    public TRole(){
        this.rid = TRole.DEFAULT_EMPLOYEE_ROLE ;
        this.name = "员工" ;
        this.purview = TPurview.USER_READ;
    }

    public TRole(Integer rid, String name, Long purview){
        this.rid = rid ;
        this.name = name ;
        this.purview = purview ;
    }

}
