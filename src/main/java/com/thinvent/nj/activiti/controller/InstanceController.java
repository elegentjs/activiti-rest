package com.thinvent.nj.activiti.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.*;

/**
 * @author liupj
 * @date 2019/02/19
 */
@Controller
@RequestMapping(path = "/instances")
public class InstanceController extends AbstractActivitController {

    @Autowired
    private SpringProcessEngineConfiguration processEngineConfiguration;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView toHtml(@RequestParam String definitionId) {
        ModelAndView modelAndView = new ModelAndView("instanceList");
        modelAndView.addObject("definitionId", definitionId);

        return modelAndView;
    }

    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {

        List<ProcessInstance> target = runtimeService.createProcessInstanceQuery().processDefinitionId((String)getQueryMsg(params).get("definitionId")).list();

        List<Map<String, Object>> instances = new ArrayList<>(target.size());

        for (ProcessInstance instance : target) {
            Map<String, Object> map = new HashMap<>(15);
            map.put("id", instance.getId());
            map.put("name", instance.getName());

            map.put("processDefinitionId", instance.getProcessDefinitionId());
            map.put("processDefinitionKey", instance.getProcessDefinitionKey());
            map.put("deploymentId", instance.getDeploymentId());

            map.put("businessKey", instance.getBusinessKey());

            map.put("processVariables", instance.getProcessVariables());
            map.put("tenantId", instance.getTenantId());

            instances.add(map);
        }

        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("result", instances);

        return ResponseEntity.ok(jsonObject);
    }


    @RequestMapping(path = "/{id}/diagram", method = RequestMethod.GET)
    public ModelAndView diagram(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView("instance-diagram");
        modelAndView.addObject("id", id);

        return modelAndView;
    }

    @RequestMapping(path = "/{id}/showDiagram", method = RequestMethod.GET)
    public void showDiagram(@PathVariable("id") String id) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        ProcessDefinition pde = repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        if (pde != null && pde.hasGraphicalNotation()) {
            BpmnModel bpmnModel = repositoryService.getBpmnModel(pde.getId());
            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            InputStream resource = diagramGenerator.generateDiagram(bpmnModel, "png", runtimeService.getActiveActivityIds(processInstance.getId()),
                    Collections.<String>emptyList(), processEngineConfiguration.getActivityFontName(), processEngineConfiguration.getLabelFontName(),
                    processEngineConfiguration.getAnnotationFontName(), processEngineConfiguration.getClassLoader(), 1.0);


            download(pde.getDiagramResourceName(), resource);
        }
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        runtimeService.deleteProcessInstance(id, "dreprecated.");
        return ResponseEntity.ok();
    }
}
