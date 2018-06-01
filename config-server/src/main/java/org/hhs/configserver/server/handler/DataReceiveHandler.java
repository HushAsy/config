package org.hhs.configserver.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.hhs.StringUtils;
import org.hhs.configserver.common.ChannelHolder;
import org.hhs.configserver.common.SpringContextUtils;
import org.hhs.configserver.service.CachedData;
import org.hhs.model.DataInfo;
import org.hhs.model.Header;
import org.hhs.model.MessageType;
import org.hhs.model.NettyMessage;
import org.springframework.context.ApplicationContext;

import javax.xml.crypto.Data;
import java.util.Map;

public class DataReceiveHandler extends ChannelHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DataInfo dataInfoReceived = (DataInfo) msg;
        CachedData cachedData = (CachedData) SpringContextUtils.getBeanByClass(CachedData.class);
        Map<String, DataInfo> maps = cachedData.getCachedMap();
        DataInfo dataInfoSend = maps.get(dataInfoReceived.getSerialDataInfoId());

        Channel channel = ctx.channel();
        if (null != dataInfoSend) {
            channel.writeAndFlush(dataInfoSend);
        }
        ChannelHolder.putChannel(dataInfoReceived, channel);
        super.channelRead(ctx, msg);
    }
}
