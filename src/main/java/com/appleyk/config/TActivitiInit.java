package com.appleyk.config;

import com.appleyk.utils.TCheckValueUtils;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

/**
 * <p></p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 22:00 2020/2/28
 */
//@Component
public class TActivitiInit implements ApplicationRunner {

    @Autowired
    private ProcessEngine processEngine;

    private static final String TAKE_LEAVE_PROCESS_KEY = "take_leave";
    private static final String SUFFIX = ".bpmn";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*RepositoryService == 流程存储服务*/
        List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery()
                .deploymentName(TAKE_LEAVE_PROCESS_KEY)
                .list();
        if(TCheckValueUtils.isEmpty(deployments)){
            Deployment deploy = processEngine.getRepositoryService()
                    .createDeployment() // 创建部署对象
                    .name(TAKE_LEAVE_PROCESS_KEY) // 声明流程的名称
                    .addClasspathResource("processes/"+TAKE_LEAVE_PROCESS_KEY+SUFFIX).deploy();
            System.out.println("部署成功："+deploy);
        }

    }
}
