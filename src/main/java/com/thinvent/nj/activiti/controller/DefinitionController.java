package com.thinvent.nj.activiti.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import com.thinvent.nj.common.util.FileUtil;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping(path = "/definitions")
public class DefinitionController extends AbstractActivitController {

    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "definitionList";
    }

    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        List<ProcessDefinition> target = repositoryService.createProcessDefinitionQuery().list();

        List<Map<String, Object>> definitions = new ArrayList<>(target.size());

        for (ProcessDefinition definition : target) {
            Map<String, Object> map = new HashMap<>(15);
            map.put("id", definition.getId());
            map.put("category", definition.getCategory());
            map.put("key", definition.getKey());
            map.put("name", definition.getName());
            map.put("resourceName", definition.getResourceName());
            map.put("diagramResourceName", definition.getDiagramResourceName());

            definitions.add(map);
        }

        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("result", definitions);

        return ResponseEntity.ok(jsonObject);
    }

    /**
     * 部署流程资源
     */
    @RequestMapping(path = "/deploy")
    @ResponseBody
    public ResponseEntity deploy(@RequestParam(value = "file") MultipartFile file) throws Exception {

        // 获取上传的文件名
        String fileName = file.getOriginalFilename();

        // 得到输入流（字节流）对象
        InputStream fileInputStream = file.getInputStream();

        // 文件的扩展名
        String extension = FileUtil.getFileExtension(fileName);

        // zip或者bar类型的文件用ZipInputStream方式部署
        DeploymentBuilder deployment = repositoryService.createDeployment();
        if ("zip".equals(extension) || "bar".equals(extension)) {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            deployment.addZipInputStream(zip);
        } else {
            // 其他类型的文件直接部署
            deployment.addInputStream(fileName, fileInputStream);
        }

        deployment.deploy();

        return ResponseEntity.ok();
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        repositoryService.deleteDeployment(repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult().getDeploymentId());

        return ResponseEntity.ok();
    }


    @RequestMapping(path = "/{id}/download")
    public void download(@PathVariable("id") String id) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();

        download(processDefinition.getResourceName(), repositoryService.getProcessModel(id));
    }


    @RequestMapping(path = "/{id}/diagram", method = RequestMethod.GET)
    public ModelAndView diagram(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView("diagram");
        modelAndView.addObject("id", id);

        return modelAndView;
    }

    @RequestMapping(path = "/{id}/showDiagram", method = RequestMethod.GET)
    public void showDiagram(@PathVariable("id") String id) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
        download(processDefinition.getDiagramResourceName(), repositoryService.getProcessDiagram(id));
    }

    @RequestMapping(path = "/{id}/instances/count", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getInstanceCount(@PathVariable("id") String id) {
        int count = runtimeService.createProcessInstanceQuery().processDefinitionId(id).list().size();

        return ResponseEntity.ok(count);
    }
}
