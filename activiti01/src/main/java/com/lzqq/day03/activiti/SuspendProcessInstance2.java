package com.lzqq.day03.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 单个流程实例挂起与激活
 *
 *
 */
public class SuspendProcessInstance2 {
//    public static void main(String[] args) {
//        //1.得到ProcessEngine对象
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        //2.得到RuntimeService
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        //3.查询流程实例对象
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId("20001").singleResult();
//
//
//        //4.得到当前流程定义对象中是否都为暂停状态
//        boolean suspended = processInstance.isSuspended();
//
//        String processInstanceId = processInstance.getId();
//
//        //5.判断
//        if(suspended){
//            //说明暂停，就可以激活操作
//            runtimeService.activateProcessInstanceById(processInstanceId);
//            System.out.println("流程:" + processInstanceId + "激活");
//        }else {
//            runtimeService.suspendProcessInstanceById(processInstanceId);
//            System.out.println("流程:" + processInstanceId + "挂起");
//        }
//
//    }

    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("zhangsan")
                .singleResult();
    }

}
