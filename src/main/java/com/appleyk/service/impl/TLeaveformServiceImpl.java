package com.appleyk.service.impl;

import com.appleyk.common.TLoggerHelper;
import com.appleyk.dao.entity.TLeaveformEntity;
import com.appleyk.dao.mapper.TLeaveformMapper;
import com.appleyk.dict.TProcessDefType;
import com.appleyk.model.TLeaveForm;
import com.appleyk.model.TProcessIns;
import com.appleyk.service.TLeaveformService;
import com.appleyk.service.TProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Appleyk
 * @since 2020-03-01
 */
@Service
public class TLeaveformServiceImpl extends ServiceImpl<TLeaveformMapper, TLeaveformEntity> implements TLeaveformService {

    @Autowired
    private TProcessService processService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TLeaveForm apply(TLeaveForm leaveForm) throws Exception {
        TLeaveformEntity entity = TLeaveformEntity.createEntity(leaveForm);
        save(entity);
        leaveForm.setId(entity.getId());
        Map<String,Object> variables = new HashMap<>();
        // 设置开启人，也就是创建了请假单的人，就是请假单流程的开启人
        variables.put("starter",leaveForm.getUser());
        //开启流程,暂不设定流程变量
        String processInsId = processService.startProcess(TProcessDefType.TAKE_LEAVE,
                leaveForm.getId(), variables);
        TLoggerHelper.info(TProcessDefType.TAKE_LEAVE.getDes()+"开启，流程实例ID："+processInsId);
        return leaveForm;
    }

    @Override
    public TProcessIns queryByBusinessKey(Long id) {
        return processService.queryByBusinessKey(id.toString());
    }
}
