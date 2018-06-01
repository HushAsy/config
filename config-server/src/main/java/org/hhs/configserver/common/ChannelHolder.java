package org.hhs.configserver.common;

import io.netty.channel.Channel;
import org.hhs.model.DataInfo;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelHolder {
    private static Map<String, List<Channel>> channels;
    private static Map<SocketAddress, String> addressStringMap;

    static {
        initChannels();
        initConnectMap();
    }

    public static Map<String, List<Channel>> initChannels(){
        if (channels == null){
            synchronized (ChannelHolder.class){
                if (channels == null){
                    channels = new ConcurrentHashMap();
                }
            }
        }
        return channels;
    }

    public static Map<SocketAddress, String> initConnectMap(){
        if (addressStringMap == null){
            synchronized (ChannelHolder.class){
                if (addressStringMap == null){
                    addressStringMap = new ConcurrentHashMap();
                }
            }
        }
        return addressStringMap;
    }

    public String getDataInfoId(SocketAddress socketAddress){
        return addressStringMap.get(socketAddress);
    }

    public static List<Channel> getChannel(String id){
        return channels.get(id);
    }

    public static boolean putChannel(DataInfo info, Channel channel){
        List<Channel> channelList = channels.get(info.getSerialDataInfoId());
        if (channelList == null){
            channelList = new ArrayList<>();
            channelList.add(channel);
            channels.put(info.getSerialDataInfoId(),channelList);
        }else{
            channelList.add(channel);
        }
        return true;
    }

    public static boolean putChannelInfo(SocketAddress socketAddress){
        return false;
    }

    public static boolean removeChannel(Channel channel){
        channels.remove(channel.id().asLongText());
        return true;
    }

    public static Map<String, List<Channel>> getChannels(){
        return channels;
    }
}
