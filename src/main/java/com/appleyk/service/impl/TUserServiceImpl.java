package com.appleyk.service.impl;

import com.appleyk.dao.entity.TUserEntity;
import com.appleyk.dao.mapper.TUserMapper;
import com.appleyk.excp.TCommonException;
import com.appleyk.model.TUser;
import com.appleyk.model.TUserRegister;
import com.appleyk.service.TUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUserEntity> implements TUserService {


    @Autowired
    private TUserMapper userMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public TUser register(TUserRegister userRegister) throws Exception{
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name",userRegister.getUserName());
        TUserEntity entity = getOne(wrapper);
        if(entity!=null){
            throw new TCommonException("用户名，"+userRegister.getUserName()+"，已经存在！");
        }
        TUserEntity userEntity = TUserEntity.createEntity(userRegister);
        userMapper.insert(userEntity);
        TUser user = new TUser(userEntity.getId(),userEntity.getName());
        return user;
    }
}
