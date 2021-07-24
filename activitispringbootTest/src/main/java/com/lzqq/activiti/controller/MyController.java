package com.lzqq.activiti.controller;

import com.lzqq.activiti.config.SecurityUtil;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;


    @RequestMapping("/hello")
    public void test01ll(){
        //1.认证
        //securityUtil.logInAs("erdemedeiros"); //指定用户认证信息
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
