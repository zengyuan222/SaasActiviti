package com.lzqq.activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例
 *      目前是先已经完成流程定义的部署工作
 */
public class ActivitiStartInstance {

    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.创建流程实例   流程定义的key需要知道  myProcess_1
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        //4.输入实例的相关信息
        System.out.println("流程部署id:"+processInstance.getDeploymentId());
        System.out.println("流程部署id:"+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id:"+processInstance.getId());
        System.out.println("活动id:"+processInstance.getActivityId());
    }

}
