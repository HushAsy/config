package org.hhs;

import org.hhs.client.NettyClient;
import org.hhs.listeners.ClientEventSource;
import org.hhs.listeners.ConfigChangeListener;
import sun.nio.ch.Net;

public class ConfigService {

    public static void addListener(String dataId,String group, ConfigChangeListener configListener){
        NettyClient.getNettyClient().connect();
        ClientEventSource.getClientEventSource().registerConfigListener(dataId, group, configListener);
    }

}
