package com.appleyk.service;

import com.appleyk.dict.TProcessDefType;
import com.appleyk.model.TProcessIns;

import java.util.Map;

/**
 * <p>流程业务接口</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 20:54 2020/3/1
 */
public interface TProcessService {

    /**
     * 开启一个流程实例
     * @param defType 流程定义枚举类对象
     * @param businessKey 上层业务key（请假单的ID）
     * @param variables 流程变量（全局）
     * @return 流程实例的ID
     */
    String startProcess(TProcessDefType defType, Long businessKey, Map<String,Object> variables)
            throws Exception;

    TProcessIns queryByBusinessKey(String businessKey);
}
