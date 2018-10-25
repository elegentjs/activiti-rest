/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thinvent.nj.activiti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tijs Rademakers
 */
@RestController
@RequestMapping(path = "/modeler")
public class ModelEditorJsonRestResource extends AbstractActivitController implements ModelDataJsonConstants {
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @RequestMapping(value="/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
  public String getEditorJson(@PathVariable String modelId) throws Exception {
    ObjectNode modelNode;
    
    Model model = repositoryService.getModel(modelId);

    if (StringUtils.isNotEmpty(model.getMetaInfo())) {
      modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
    } else {
      modelNode = objectMapper.createObjectNode();
      modelNode.put(MODEL_NAME, model.getName());
    }

    modelNode.put(MODEL_ID, model.getId());
    ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
            new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
    modelNode.set("model", editorJsonNode);

    render(modelNode.toString());

    return null;
  }
}
