package com.thinvent.nj.activiti.controller;

import com.alibaba.fastjson.JSONObject;
import com.thinvent.nj.common.rest.ResponseEntity;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/models")
public class ModelController extends AbstractActivitController {

    @RequestMapping(method = RequestMethod.GET)
    public String toHtml() {
        return "modelList";
    }

    @RequestMapping(path = "/search/page", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity findTableData(@RequestBody Map<String, Object> params) {
        List<Model> target = repositoryService.createModelQuery().list();

        List<Map<String, Object>> definitions = new ArrayList<>(target.size());

        for (Model model : target) {
            Map<String, Object> map = new HashMap<>(15);
            map.put("id", model.getId());
            map.put("category", model.getCategory());
            map.put("key", model.getKey());
            map.put("name", model.getName());
            map.put("metaInfo", model.getMetaInfo());
            map.put("deploymentId", model.getDeploymentId());
            map.put("createTime", model.getCreateTime());
            map.put("lastUpdateTime", model.getLastUpdateTime());
            map.put("version", model.getVersion());
            map.put("tenantId", model.getTenantId());

            definitions.add(map);
        }

        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("result", definitions);

        return ResponseEntity.ok(jsonObject);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity del(@PathVariable("id") String id) {
        repositoryService.deleteModel(id);

        return ResponseEntity.ok();
    }
}
