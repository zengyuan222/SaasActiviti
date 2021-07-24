package com.lzqq.activiti;

import jdk.nashorn.internal.ir.LiteralNode;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 *  处理当前用户的任务
 *  背后操作的表：
 *      act_hi_actinst
 *      act_hi_identitylink
 *      act_hi_taskinst
 *      act_ru_execution
 *      act_ru_identitylink
 *      act_ru_task
 */
public class ActivitiCompleteTask {

    // lishi完成自己任务
    public static void main(String[] args) {
        List<String> idList = new ArrayList<>();
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_2")
                .taskAssignee("zhangsan")   // 如果只是有一个任务可以用  singleResult()  否则只能加.taskAssignee("lishi").list()
                .list();
        //4.任务列表的展示
        for(Task task : taskList){
            System.out.println(" 流 程 实 例 id ： " +task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            idList.add(task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

        //3.处理任务,结合当前用户任务列表的查询操作的话，任务ID:2505
        for(String  id: idList){
            taskService.complete(id);
        }

    }

    // 张三完成自己任务

//    public static void main(String[] args) {
//        List<String> idList = new ArrayList<>();
//        //1.得到ProcessEngine对象
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        //2.得到TaskService对象
//        TaskService taskService = processEngine.getTaskService();
//        List<Task> taskList = taskService.createTaskQuery()
//                .processDefinitionKey("myProcess_1")
//                .taskAssignee("zhangsan")
//                .list();
//        //4.任务列表的展示
//        for(Task task : taskList){
//            System.out.println(" 流 程 实 例 id ： " +task.getProcessInstanceId());
//            System.out.println("任务id：" + task.getId());
//            idList.add(task.getId());
//            System.out.println("任务负责人：" + task.getAssignee());
//            System.out.println("任务名称：" + task.getName());
//        }
//
//        //3.处理任务,结合当前用户任务列表的查询操作的话，任务ID:2505
//        for(String  id: idList){
//            taskService.complete(id);
//        }
//
//    }

}
