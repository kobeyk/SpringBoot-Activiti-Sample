package com.appleyk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * <p>流程实例，对应一个业务对象</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 19:19 2020/3/1
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TProcessIns {

    /**业务key,也就是项目中的请假单ID（唯一标识）*/
    private String businessKey;

    /**一个流程实例，包含了多个任务节点*/
    List<TTaskNode> tasks;

    /**流程实例变量*/
    private Map<String,Object> variables;


}
