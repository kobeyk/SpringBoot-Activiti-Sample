package com.appleyk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

/**
 * <p>任务节点</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 19:20 2020/3/1
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TTaskNode extends TObject{

    /**任务节点存储的变量*/
    private Map<String,Object> variables;

    /**任务节点谁发起的*/
    private TUser user ;

}
