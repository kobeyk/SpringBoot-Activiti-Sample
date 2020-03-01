package com.appleyk.controller;


import com.appleyk.common.TResult;
import com.appleyk.excp.TCommonException;
import com.appleyk.model.TUserRegister;
import com.appleyk.service.TUserService;
import com.appleyk.utils.TCheckValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class TUserController {

    @Autowired
    private TUserService userService;

    @PostMapping("/register")
    public TResult register(@RequestBody TUserRegister register) throws Exception{
        String userName = register.getUserName();
        String password = register.getPassword();
        String rePassword = register.getRePassword();
        if(TCheckValueUtils.isEmpty(userName)){
            throw new TCommonException("用户名不能为空！");
        }
        if(TCheckValueUtils.isEmpty(password)){
            throw new TCommonException("密码不能为空！");
        }
        if(!password.equals(rePassword)){
            throw new TCommonException("两次输入的密码不一致！");
        }
        return TResult.ok(userService.register(register));
    }

}

