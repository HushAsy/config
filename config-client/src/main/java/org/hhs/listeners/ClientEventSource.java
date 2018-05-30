package org.hhs.listeners;

import org.hhs.ConfigService;
import org.hhs.StringUtils;
import org.hhs.model.DataInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientEventSource {
    private static ClientEventSource clientEventSource = null;
    public static void initClientEventSource(){
        if (clientEventSource == null){
            synchronized (ConfigService.class){
                if (clientEventSource == null){
                    clientEventSource = new ClientEventSource();
                }
            }
        }
    }

    //记录
    private Map<String, ConfigChangeListener> listeners = new ConcurrentHashMap<>();

    public void registerConfigListener(String dataId,String group, ConfigChangeListener configListener){
        listeners.put(StringUtils.getStringId(dataId,group), configListener);
    }

    public void removeConfigChangeListener(String dataId,String group){
        listeners.remove(StringUtils.getStringId(dataId,group));
    }

    public void updateMessage(DataInfo dataInfo){
        ConfigChangeListener configChangeListener = listeners.get(StringUtils.getStringId(dataInfo.getDataId(),dataInfo.getGroup()));
        configChangeListener.receiveConfigInfo(dataInfo.getContent());
    }

    public static ClientEventSource getClientEventSource(){
        return clientEventSource;
    }
}
