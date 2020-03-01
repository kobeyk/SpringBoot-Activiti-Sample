package com.appleyk.common;

import com.appleyk.dict.TBusinessCode;
import com.appleyk.excp.TCommonException;
import com.appleyk.model.TRole;
import com.appleyk.model.TUser;

/**
 * <p>权限</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 21:40 2020/2/29
 */
public class TPurview {

    /**查看用户信息 2<<0*/
    public static final Long USER_READ = 2L;
    /**财务审批权限 2<<9*/
    public static final Long FINANCIAL_APPROVAL = 1024L;
    /**员工请假条审批权限 2<<10*/
    public static final Long LEAVE_PERMIT_APPROVAL = 2048L;
    /**新开/新增/创建流程权限 2<<11*/
    public static final Long CREATE_PROCESS = 4096L;
    /**删除流程权限 2<<12*/
    public static final Long DELETE_PROCESS = 8192L;
    /**系统管理（后台）权限  -- 所有权限都有*/
    public static final Long SYSTEM_BACKGROUND = USER_READ|FINANCIAL_APPROVAL | LEAVE_PERMIT_APPROVAL
            | CREATE_PROCESS | DELETE_PROCESS ;

    /**
     * <p>判断用户是否具有某个权限</p>
     * @param user 登录的用户（全信息）
     * @param purview（权限值）
     * @return 有：true  没有：false
     */
    public static Boolean hasPurview(TUser user , Long purview) throws TCommonException {
        TRole role = user.getRole();
        if(role == null){
            throw  new TCommonException(TBusinessCode.INVALID_GRANT,"用户授权失败，角色空！");
        }

        Long userPurview = role.getPurview();
        if(userPurview == null){
            throw  new TCommonException(TBusinessCode.INVALID_GRANT,"用户授权失败，权限空！");
        }

        return (userPurview & purview ) ==  purview;
    }

    public static void main(String[] args) throws TCommonException{

        /**员工请假条审批权限*/
        long purview = 2048;
        TRole role = new TRole(TRole.SUPER_MANAGER_ROLE,"系统超管" ,SYSTEM_BACKGROUND);
        TUser user = new TUser(System.currentTimeMillis(),"Appleyk",role);
        System.out.println(role.getPurview() & purview);
        if(hasPurview(user,purview)){
            System.out.println("用户："+user.getName()+",ID："+user.getId()+",具有该权限！");
        }

    }

}
