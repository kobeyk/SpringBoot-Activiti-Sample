package com.appleyk;

import com.appleyk.common.TPurview;
import com.appleyk.model.TRole;
import com.appleyk.model.TLeaveForm;
import com.appleyk.model.TUser;
import com.appleyk.utils.TCheckValueUtils;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TActivitiTest {

    @Autowired
    private ProcessEngine processEngine;

    /*部署查询*/
    @Test
    public void queryDeployMents() {
        List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery()
                .orderByDeploymenTime().desc().list();
        for (Deployment deployment : deployments) {
            System.out.println("部署ID："+deployment.getId());
            System.out.println("部署名称："+deployment.getName());
            System.out.println("部署时间："+deployment.getDeploymentTime());
        }
    }

    /*开启一个流程实例，其实就是针对部署，new一个流程实例*/
    @Test
    public void startProcessInstance() {
        Long leaveId = System.currentTimeMillis();
        Map<String,Object> variables  = new HashMap<>();
        variables.put("dept","研发部");
        variables.put("year","2020");
        ProcessInstance takeProcIns = processEngine.getRuntimeService()
                .startProcessInstanceByKey("take_leave",leaveId.toString(),variables);
        System.out.println("流程实例ID："+takeProcIns.getId());
        System.out.println("流程定义ID："+takeProcIns.getProcessDefinitionId());
        System.out.println("流程实例关联的业务Key(请假单ID)："+takeProcIns.getBusinessKey());
        /**查看请假流程实例，对应的全部变量，也就是谁发起的请假单*/
        Map<String, VariableInstance> variableInstances = processEngine.getRuntimeService().getVariableInstances(takeProcIns.getId());
        System.out.println("流程实例对应的全局变量(部门)："+variableInstances.get("dept").getValue());
        System.out.println("流程实例对应的全局变量(年份)："+variableInstances.get("year").getValue());
    }

    /*查看任务列表*/
    @Test
    public void getTask(){

        List<Task> tasks = processEngine.getTaskService().createTaskQuery()
                .processDefinitionKey("take_leave").orderByTaskCreateTime().desc().list();

        for (Task task : tasks) {
            Map<String, Object> variables = processEngine.getTaskService().getVariables(task.getId());
            System.out.println("任务ID："+task.getId());
            System.out.println("任务名称："+task.getName());
            System.out.println("任务受理人："+task.getAssignee());
            System.out.println("任务对应的请假单ID："+task.getBusinessKey());
            if(variables.get("dept") !=null){
                System.out.println("(全局变量)部门："+variables.get("dept"));
            }
            if(variables.get("year") !=null){
                System.out.println("(全局变量)年份："+variables.get("year"));
            }
            if(variables.get("taker") == null){
                System.out.println("========== 分割线 ============");
                continue;
            }
            TLeaveForm taker = (TLeaveForm) variables.get("taker");
            if(taker!=null){
                System.out.println("请假人："+taker.getUser().getName());
                System.out.println("请假天数："+taker.getDays());
                System.out.println("请假理由："+taker.getReason());
            }
            if(variables.get("approver") ==null){
                System.out.println("========== 分割线 ============");
                continue;
            }
            TUser approver= (TUser) variables.get("approver");
            if(approver!=null){
                System.out.println("审批人："+approver.getName());
                System.out.println("审批人角色："+approver.getRole().getName());
                System.out.println("审批人权限："+approver.getRole().getPurview());
            }
            if(variables.get("backReason") !=null){
                System.out.println("审批人打回理由："+variables.get("backReason"));
            }
            System.out.println("========== 分割线 ============");
        }
    }


    /*执行任务（申请单）*/
    @Test
    public void completeTask(){

        // 申请人要完成的任务，也就是执行这个任务Id，跳到下一个流程(审批)
        String taskId = "242510";
        //构建请假条
        Long leaveId = 1583054752024L;
        TLeaveForm taker = new TLeaveForm(leaveId, "事假",5,"世界太大，我还是想去看看！");
        taker.setUser(new TUser(666L,"Appleyk",new TRole()));
        Map<String,Object> variables = new HashMap<>();
        variables.put("taker",taker);
        TaskService taskService = processEngine.getTaskService();
        // 设置变量，主要就是申请人填写请假单信息，信息包括请假天数、理由
//        taskService.setVariablesLocal(taskId,variables);
        taskService.setVariables(taskId,variables);
        taskService.setAssignee(taskId,"666");
        taskService.complete(taskId,variables);
        System.out.println("请假条提交成功！");
    }


    /**执行任务，审批请假单*/
    @Test
    public void completeTaskForApproval(){

        // 申请人要完成的任务，也就是执行这个任务Id，跳到下一个流程(审批)
        String taskId = "250004";
        //构建请假条
        TUser approver =new TUser(7777L,"孙甜甜",new TRole(TRole.HUMAN_AFFAIRS,"人事",TPurview.LEAVE_PERMIT_APPROVAL));
//        TUser approver =new TUser(8888L,"张大力",new TRole(TRole.PROJECT_MANAGER_ROLE,"组长",TPurview.LEAVE_PERMIT_APPROVAL));
//        TUser approver =new TUser(9999L,"余总",new TRole(TRole.GENERAL_MANAGER_ROLE,"总经理",TPurview.LEAVE_PERMIT_APPROVAL));
        Map<String,Object> variables = new HashMap<>();
        variables.put("approver",approver);
        // 假如，审批不同意
        variables.put("agree",1);
//        variables.put("backReason","你请假太多了，会影响工作进度的，请考虑下缩短请假时间！");
        TaskService taskService = processEngine.getTaskService();
        // 设置变量，审批人
        taskService.setVariables(taskId,variables);
//        taskService.setVariablesLocal(taskId,variables);
        taskService.setAssignee(taskId,approver.getId().toString());
        taskService.complete(taskId,variables);
        System.out.println("审批任务完成！");
    }


    /*查看系统中所有的流程定义*/
    @Test
    public void queryProcessDefinition(){
        List<ProcessDefinition> definitions = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .latestVersion().orderByProcessDefinitionVersion().desc().list();
        for (ProcessDefinition definition : definitions) {
            System.out.println("流程定义ID："+definition.getId());
            System.out.println("流程定义Key："+definition.getKey());
            System.out.println("流程定义名称："+definition.getName());
            System.out.println("流程定义类别："+definition.getCategory());
            System.out.println("流程定义对应的部署ID："+definition.getDeploymentId());
            System.out.println("流程定义的版本："+definition.getVersion());
            System.out.println("流程定义的资源BPMN文件名称："+definition.getResourceName());
            System.out.println("流程定义的资源图片文件名称："+definition.getDiagramResourceName());
            System.out.println("========== 分割线 ============");
        }
    }

    /*查看流程实例的状态,也就是流程定义只有一个，但是流程实例可以有多个，一个审批就是一个流程实例，但是它对应一个流程定义*/
    @Test
    public void queryProcessInstanceState(){
        List<ProcessInstance> instances = processEngine.getRuntimeService().createProcessInstanceQuery().list();
        if(TCheckValueUtils.isNotEmpty(instances)){
            for (ProcessInstance processInstance : instances) {
                System.out.println("正在运行中的流程：");
                System.out.println("流程实例ID："+processInstance.getId());
                System.out.println("流程实例Name："+processInstance.getName());
                System.out.println("流程实例对应的上层业务Key："+processInstance.getBusinessKey());
                System.out.println("========== 分割线 ============");
            }
        }else {
            System.out.println("当前无正在运行的流程实例..");
        }
    }

    /*删除部署*/
    @Test
    public void deleteDeployment(){
        processEngine.getRepositoryService().deleteDeployment("8xxxx");
    }

    /*查看历史流程*/
    @Test
    public void queryHistoryProcInst(){
        List<HistoricProcessInstance> procInstances = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance procInstance : procInstances) {
            System.out.println("流程实例ID："+procInstance.getId());
            System.out.println("流程实例名称："+procInstance.getName());
            System.out.println("流程实例关联的业务Key："+procInstance.getBusinessKey());
            System.out.println("========== 分割线 ============");
        }
    }

    /*查看指定业务Key的历史流程*/
    @Test
    public void queryDoneProcInst(){
        HistoricProcessInstance processInstance = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery().processInstanceBusinessKey("1583054752024").singleResult();

        /**查看请假流程实例，对应的全部变量，也就是谁发起的请假单*/
        List<HistoricVariableInstance> variableInstances = processEngine.getHistoryService().createHistoricVariableInstanceQuery()
                .processInstanceId(processInstance.getId()).list();

        System.out.println("流程实例ID："+processInstance.getId());
        System.out.println("流程实例名称："+processInstance.getName());
        System.out.println("流程实例关联的业务Key："+processInstance.getBusinessKey());
        System.out.println("流程实例对应的全局变量："+variableInstances);
        System.out.println("========== 分割线 ============");

    }


    /*查看指定业务Key的历史完整流程*/
    @Test
    public void queryActivitiProcInst(){
        HistoricProcessInstance processInstance = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery().processInstanceBusinessKey("1583054752024").singleResult();
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId())
                .list();

        for (HistoricActivityInstance historicActivityInstance : list) {
            if(historicActivityInstance.getActivityName() !=null){
                System.out.println("流程："+historicActivityInstance.getActivityName());
            }

        }


    }

    /*查看历史任务*/
    @Test
    public void queryHistoryTaskInst(){
        List<HistoricTaskInstance> taskInstances = processEngine.getHistoryService().createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance taskInstance : taskInstances) {
            System.out.println("任务ID："+taskInstance.getId());
            System.out.println("任务名称："+taskInstance.getName());
            System.out.println("任务处理人："+taskInstance.getAssignee());
            System.out.println("任务变量(流程中同名任务变量不存在覆盖)："+taskInstance.getTaskLocalVariables());
            System.out.println("任务关联的业务Key："+taskInstance.getBusinessKey());
            System.out.println("========== 分割线 ============");
        }
    }



}
