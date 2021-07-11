package com.lzqq.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 查询当前用户的任务列表
 */
public class ActivitiTaskQuery {

    //lishi完成自己任务列表
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("zhangsan")
                .list();
        //4.任务列表的展示
        for(Task task : taskList){
            System.out.println(" 流 程 实 例 id ： " +task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());

        }

    }


    //张三任务列表的查询

//    public static void main(String[] args) {
//        //1.得到ProcessEngine对象
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        //2.得到TaskService对象
//        TaskService taskService = processEngine.getTaskService();
//        //3.根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
//        List<Task> taskList = taskService.createTaskQuery()
//                .processDefinitionKey("myProcess_1")
//                .taskAssignee("zhangsan")
//                .list();
//        //4.任务列表的展示
//        for(Task task : taskList){
//            System.out.println(" 流 程 实 例 id ： " +task.getProcessInstanceId());
//            System.out.println("任务id：" + task.getId());
//            System.out.println("任务负责人：" + task.getAssignee());
//            System.out.println("任务名称：" + task.getName());
//
//        }
//
//    }
}
