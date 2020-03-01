package com.appleyk.service;

import com.appleyk.dao.entity.TUserEntity;
import com.appleyk.model.TUser;
import com.appleyk.model.TUserRegister;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
public interface TUserService extends IService<TUserEntity> {
     TUser register(TUserRegister userRegister) throws Exception;
}
