package org.hhs.configserver.service;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.hhs.configserver.EventListener.EventListener;
import org.hhs.configserver.EventListener.EventSource;
import org.hhs.configserver.common.ChannelHolder;
import org.hhs.configserver.server.NettyServer;
import org.hhs.model.DataInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        eventSource.registerListener("hush", new EventListener<DataInfo>() {
            @Override
            public void eventHandler(DataInfo dataInfo) {
                Map<String, List<Channel>> channelMap = ChannelHolder.getChannels();
                List<Channel> channels = channelMap.get(dataInfo.getSerialDataInfoId());
                for (Channel channel:channels){
                    if (channel.isActive()){
                        channel.writeAndFlush(dataInfo);
                    }else{
                        channel.close().addListener(new GenericFutureListener<Future<? super Void>>() {
                            @Override
                            public void operationComplete(Future<? super Void> future) throws Exception {
                                System.out.println(channel.remoteAddress()+ "has close");
                            }
                        });
                    }
                }
            }
        });
    }
}
