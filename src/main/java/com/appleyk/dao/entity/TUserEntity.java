package com.appleyk.dao.entity;

import com.appleyk.common.TPurview;
import com.appleyk.model.TRole;
import com.appleyk.model.TUser;
import com.appleyk.model.TUserRegister;
import com.appleyk.utils.TMd5EncryptUtils;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class TUserEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    private String name;

    private String password;

    private Integer role;

    private Long purview;

    private String alias;

    private String avatar;

    private Date ctime = new Date();

    private Date utime = new Date();

    private TUserEntity(TUserRegister register) throws Exception{
        this.name = register.getUserName();
        this.password = TMd5EncryptUtils.build(register.getPassword());
        this.role = TRole.DEFAULT_EMPLOYEE_ROLE;
        this.purview = TPurview.USER_READ;
    }

    public static TUserEntity createEntity(TUserRegister register) throws Exception{
        return new TUserEntity(register);
    }

    public static TUser createModel(TUserEntity userEntity) throws Exception{
        TUser user = new TUser(userEntity.getId(),userEntity.getName(),
                userEntity.getAlias(),userEntity.getAvatar());
        TRole role = new TRole(userEntity.getRole(),"",userEntity.getPurview());
        user.setRole(role);
        return user;
    }

}
