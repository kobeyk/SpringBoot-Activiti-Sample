package com.appleyk.service;

import com.appleyk.dao.entity.TLeaveformEntity;
import com.appleyk.model.TLeaveForm;
import com.appleyk.model.TProcessIns;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
public interface TLeaveformService extends IService<TLeaveformEntity> {
    TLeaveForm apply(TLeaveForm leaveForm) throws Exception;
    TProcessIns queryByBusinessKey(Long id);
}
