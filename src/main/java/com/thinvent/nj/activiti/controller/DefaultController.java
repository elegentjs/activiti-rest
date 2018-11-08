package com.thinvent.nj.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping(path = "/index")
    public String index() {
        return "redirect:/models";
    }
}
