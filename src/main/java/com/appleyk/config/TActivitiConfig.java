package com.appleyk.config;

import org.activiti.engine.*;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p>Activiti配置</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/Appleyk
 * @date created on 15:00 2020/2/28
 */
@Configuration
public class TActivitiConfig {

    /*统一数据源*/
    @Autowired
    private DataSource dataSource;

    /*统一事务管理器*/
    @Autowired
    private DataSourceTransactionManager transactionManager;

    /*初始化25张表*/
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        // 启动自动部署流程
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:processes/*.bpmn");
            configuration.setDeploymentResources(resources);
            configuration.setDeploymentName("deployment");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         DB_SCHEMA_UPDATE_FALSE = "false";//不能自动创建表，需要表存在
         DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";//先删除表再创建表
         DB_SCHEMA_UPDATE_TRUE = "true";//如果表不存在，自动创建表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        /*是否在流程引擎启动就激活**/
        configuration.setAsyncExecutorActivate(false);
        System.out.println("核心发动机："+configuration.buildProcessEngine());
        return configuration;
    }

    /*超级发动机登场*/
    @Bean
    public ProcessEngine processEngine(@Qualifier("processEngineConfiguration")SpringProcessEngineConfiguration configuration){
        return configuration.buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(@Qualifier("processEngine") ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }


}
