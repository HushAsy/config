package org.hhs.configserver.service;

import io.netty.channel.Channel;
import org.hhs.configserver.EventListener.EventListener;
import org.hhs.configserver.EventListener.EventSource;
import org.hhs.configserver.common.ChannelHolder;
import org.hhs.configserver.server.NettyServer;
import org.hhs.model.DataInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InitConfig implements InitializingBean {
    @Autowired
    private CachedData cachedData;

    @Autowired
    private EventSource eventSource;

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void afterPropertiesSet() throws Exception {
        nettyServer.bind();
        eventSource.registerListener("hello", new EventListener<DataInfo>() {

            @Override
            public void eventHandler(DataInfo dataInfo) {
                Map<String, Channel> channelMap = ChannelHolder.getChannels();
                for (Map.Entry<String, Channel> entry : channelMap.entrySet()){
                    Channel channel = entry.getValue();
                    if (channel.isActive()){
                        channel.writeAndFlush(dataInfo);
                    }else{
                        System.out.println(channel.toString()+" is not active");
                    }

                }
            }
        });
    }
}
