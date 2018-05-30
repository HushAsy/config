package org.hhs.configserver.controller;

import org.hhs.StringUtils;
import org.hhs.configserver.service.CachedData;
import org.hhs.model.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("config")
public class UpdateDataController {

    @Autowired
    private CachedData cachedData;

    @ResponseBody
    @RequestMapping("update.do")
    public String update(DataInfo obj){
        cachedData.putData(obj);
        return "success";
    }

    @ResponseBody
    @RequestMapping("delete.do")
    public String delete(DataInfo obj){
        cachedData.removeData(StringUtils.getStringId(obj.getDataId(),obj.getGroup()));
        return "success";
    }
}
