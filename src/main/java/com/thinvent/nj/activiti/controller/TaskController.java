package com.thinvent.nj.activiti.controller;

import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.StringUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务Controller
 *
 * 本Controller是对标准activiti-rest的补充
 *
 * @author liupeijun
 */
@RestController
@RequestMapping(path = "/runtime")
public class TaskController extends AbstractActivitController {

    @RequestMapping(path="/businessKeys", method = RequestMethod.GET)
    public ResponseEntity getBusinessKeys(@RequestParam Map<String, Object> params) {
        String userId = (String) params.get("userId");
        String processDefinitionKey = (String) params.get("processDefinitionKey");

        if (StringUtil.isNullOrEmpty(userId)) {
            throw new IllegalArgumentException("params must contains userId");
        }

        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().active().processDefinitionKey(processDefinitionKey).list();

        logger.info("processInstances length : " + processInstances.size());

       List<Task> taskList = taskService.createTaskQuery().taskCandidateOrAssigned(userId)
                   .processDefinitionKey(processDefinitionKey).active().list();

       logger.info("taskList length : " + taskList.size());
       for (Task item : taskList) {
           logger.info("instanceId : " + item.getProcessInstanceId() + ", " + item);
       }

        Map<String, String> businessKeyTaskIdMap = new HashMap<>(taskList.size());
        for (Task item : taskList) {
            ProcessInstance instance = findTargetInstance(item.getProcessInstanceId(), processInstances);
            businessKeyTaskIdMap.put(instance.getBusinessKey(), item.getId());
        }

        logger.info("businessKeyTaskIdMap : " + businessKeyTaskIdMap);

        return ResponseEntity.ok(businessKeyTaskIdMap);
    }


    private ProcessInstance findTargetInstance(String instanceId, List<ProcessInstance> instances) {
        ProcessInstance result = null;

        for (ProcessInstance item : instances) {
            if (item.getId().equals(instanceId)) {
                result = item;
                break;
            }
        }

        return result;
    }

    /**
     * 任务反签收
     * @param taskId
     * @return
     */
    @RequestMapping(path = "/tasks/{id}/unclaim", method = RequestMethod.POST)
    public ResponseEntity unClaim(@PathVariable("id") String taskId) {
        taskService.unclaim(taskId);
        return ResponseEntity.ok();
    }
}
