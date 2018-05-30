package org.hhs.configserver.controller;

import org.hhs.configserver.service.CachedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private CachedData data;

    @ResponseBody
    @RequestMapping("data.do")
    public String getData(){
        return data.getCachedMap().toString();
    }
}
