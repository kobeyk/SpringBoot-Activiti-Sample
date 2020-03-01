package com.appleyk.service.impl;

import com.appleyk.dict.TProcessDefType;
import com.appleyk.excp.TCommonException;
import com.appleyk.model.TProcessIns;
import com.appleyk.model.TTaskNode;
import com.appleyk.service.TProcessService;
import com.appleyk.utils.TCheckValueUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 20:55 2020/3/1
 */
@Service
public class TProcessServiceImpl implements TProcessService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService service ;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String startProcess(TProcessDefType defType, Long businessKey, Map<String, Object> variables) throws Exception{
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(defType.getName(), businessKey.toString(), variables);
        if(processInstance == null){
            throw new TCommonException(defType.getDes()+"启动失败，对应请假单号："+businessKey);
        }
        return processInstance.getId();
    }

    @Override
    public TProcessIns queryByBusinessKey(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey)
                .singleResult();

        Map<String, VariableInstance> variableInstances = runtimeService.getVariableInstances(processInstance.getId());
        Map<String,Object> variables = new HashMap<>();
        for (Map.Entry<String, VariableInstance> entry : variableInstances.entrySet()) {
            String key = entry.getKey();
            VariableInstance value = entry.getValue();
            variables.put(key,value.getValue());
        }
        if(TCheckValueUtils.isEmpty(processInstance.getId())){
            return null;
        }
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId())
                .active().singleResult();
        TProcessIns processIns = new TProcessIns();
        List<TTaskNode> nodes = new ArrayList<>();
        TTaskNode taskNode = new TTaskNode();
        taskNode.setId(Long.valueOf(task.getId()));
        taskNode.setName(task.getName());
        try{
            Map<String, Object> taskVariables = runtimeService.getVariables(task.getId());
            taskNode.setVariables(taskVariables);
        }catch (Exception e){

        }
        nodes.add(taskNode);
        processIns.setBusinessKey(businessKey);
        processIns.setTasks(nodes);
        processIns.setVariables(variables);
        return processIns;
    }
}
