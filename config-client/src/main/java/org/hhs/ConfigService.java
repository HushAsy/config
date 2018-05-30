package org.hhs;

import org.hhs.client.NettyClient;
import org.hhs.listeners.ClientEventSource;
import org.hhs.listeners.ConfigChangeListener;

public class ConfigService {

    private static NettyClient nettyClient = new NettyClient();

    static {
        ClientEventSource.initClientEventSource();
        try {
            nettyClient.connect("127.0.0.1", 8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addListener(String dataId,String group, ConfigChangeListener configListener){
        ClientEventSource.getClientEventSource().registerConfigListener(dataId, group, configListener);
    }

}
