package org.hhs.configserver.EventListener;

import org.hhs.model.DataInfo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventSource {

    private Map<String, EventListener> listeners = new ConcurrentHashMap<>();

    public void registerListener(String name, EventListener eventListener){
        listeners.put(name, eventListener);
    }

    public Object removeListener(String name){
        return listeners.remove(name);
    }

    public void updateData(DataInfo obj){
        for(Map.Entry<String, EventListener> entry:listeners.entrySet()){
            EventListener eventListener = entry.getValue();
            eventListener.eventHandler(obj);
        }
    }

    public void updateData(String id, Object o){
        EventListener eventListener = listeners.get(id);
        eventListener.eventHandler(o);
    }

}
