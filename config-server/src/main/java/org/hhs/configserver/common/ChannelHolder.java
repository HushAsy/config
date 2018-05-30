package org.hhs.configserver.common;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelHolder {
    private static Map<String, Channel> channels;

    static {
        initChannels();
    }

    public static Map<String, Channel> initChannels(){
        if (channels == null){
            synchronized (ChannelHolder.class){
                if (channels == null){
                    channels = new ConcurrentHashMap();
                }
            }
        }
        return channels;
    }

    public static Channel getChannel(String id){
        return channels.get(id);
    }

    public static boolean putChannel(Channel channel){
        channels.putIfAbsent(channel.id().asLongText(),channel);
        return true;
    }

    public static boolean removeChannel(Channel channel){
        channels.remove(channel.id().asLongText());
        return true;
    }

    public static Map<String, Channel> getChannels(){
        return channels;
    }
}
