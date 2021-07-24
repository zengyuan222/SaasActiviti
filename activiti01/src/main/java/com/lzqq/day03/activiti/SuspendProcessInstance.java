package com.lzqq.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 实现流程实例挂起与激活
 *
 *
 */
public class SuspendProcessInstance {
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.查询流程实例
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_1").singleResult();

        //4.得到当前流程定义对象中是否都为暂停状态
        boolean suspended = processDefinition.isSuspended();

        String processDefinitionId = processDefinition.getId();

        //5.判断
        if(suspended){
            //说明暂停，就可以激活操作
            repositoryService.activateProcessDefinitionById(processDefinitionId,true,null);
            System.out.println("流程实例:" + processDefinitionId + "激活");
        }else {
            repositoryService.suspendProcessDefinitionById(processDefinitionId,true,null);
            System.out.println("流程实例:" + processDefinitionId + "挂起");
        }

    }
}
