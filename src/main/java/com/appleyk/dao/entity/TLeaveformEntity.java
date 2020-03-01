package com.appleyk.dao.entity;

import com.appleyk.dict.TLeaveFormType;
import com.appleyk.model.TLeaveForm;
import com.appleyk.model.TUser;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("t_leaveform")
public class TLeaveformEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    private String name;

    private Long uid;

    private Integer days;

    private String reason;

    private String status;

    private Long utime = System.currentTimeMillis();

    private Integer type;

    private TLeaveformEntity(TLeaveForm leaveForm){
        this.name = leaveForm.getName();
        this.uid = leaveForm.getUser().getId();
        this.days = leaveForm.getDays();
        this.reason = leaveForm.getReason();
        this.status = leaveForm.getStatus();
        this.type = leaveForm.getLeaveType().getCode();
    }

    public static TLeaveformEntity createEntity(TLeaveForm leaveForm){
        return new TLeaveformEntity(leaveForm);
    }

    public static TLeaveForm createModel(TLeaveformEntity entity){
        TLeaveForm leaveForm = new TLeaveForm(entity.getId(),entity.getName(),
                entity.getDays(),entity.getReason());
        TUser user =new TUser();
        user.setId(entity.getUid());
        leaveForm.setUser(user);
        leaveForm.setLeaveType(TLeaveFormType.getLeaveType(entity.getType()));
        leaveForm.setStatus(entity.getStatus());
        return leaveForm;

    }

}
