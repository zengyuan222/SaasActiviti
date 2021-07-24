package com.lzqq.activiti;


import com.lzqq.activiti.config.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    ProcessRuntime processRuntime;  //流程实例定义相关操作

    @Autowired
    TaskRuntime taskRuntime;  //实现任务相关操作类

    @Autowired
    SecurityUtil securityUtil;  //springSecurity相关的工具类

    // 流程定义    注意：流程部署工作在activiti7与springboot整合后，会自动部署 +>>  resource/processes/*.bpmn
    @Test
    public void test01(){
        securityUtil.logInAs("salaboy");  //springsecurity的认证工作
        //分页查询出流程定义信息
        Page processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));

        System.out.println("查看已部署的流程个数:"+processDefinitionPage.getTotalItems());

        processDefinitionPage.getContent();  //得到当前部署的每一个流程定义的信息
        for (Object pd : processDefinitionPage.getContent()){
            System.out.println(pd);
        }
    }


    //启动流程实例
    @Test
    public void test02(){
        securityUtil.logInAs("salaboy");  //springsecurity的认证工作
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("myProcess_1")
                .build());  //启动流程实例

        System.out.println(processInstance.getId());


    }

    //查询任务，并完成任务
    @Test
    public void test03(){
        //1.认证
        securityUtil.logInAs("erdemedeiros"); //指定用户认证信息
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10)); //分页查询任务列表
        if(taskPage.getTotalItems()>0){
            //说明有任务
            for(Task task:taskPage.getContent()){
                System.out.println("任务1:"+task);
                //拾取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                //执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        }
        //再次查询新任务
        taskPage = taskRuntime.tasks(Pageable.of(0, 10)); //分页查询任务列表
        if(taskPage.getTotalItems()>0){
            //说明有任务
            for(Task task:taskPage.getContent()){
                System.out.println("任务2:"+task);
            }
        }
    }



}
