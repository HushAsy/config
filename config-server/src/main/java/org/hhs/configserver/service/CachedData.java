package org.hhs.configserver.service;

import org.hhs.StringUtils;
import org.hhs.configserver.EventListener.EventSource;
import org.hhs.model.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CachedData {

    @Autowired
    private EventSource eventSource;

    //存放客户端连接的数据 每一个连接对应一条数据
    private Map<String, DataInfo> cachedMap = new ConcurrentHashMap();

    public void putData(DataInfo dataInfo){
        cachedMap.put(StringUtils.getStringId(dataInfo.getDataId(),dataInfo.getGroup()), dataInfo);
        updateClientData(dataInfo);
    }

    public void removeData(String serialNum){
        cachedMap.remove(serialNum);
    }

    public void updateClientData(DataInfo dataInfo){
        eventSource.updateData(dataInfo);
    }

    public Map<String, DataInfo> getCachedMap() {
        return cachedMap;
    }
}
