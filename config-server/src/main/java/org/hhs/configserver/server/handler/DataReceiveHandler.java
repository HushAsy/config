package org.hhs.configserver.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.hhs.StringUtils;
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
        super.channelRead(ctx, msg);
        DataInfo dataInfoReceived = (DataInfo) msg;
        CachedData cachedData = (CachedData) SpringContextUtils.getBeanById("cachedData");
        Map<String, DataInfo> maps = cachedData.getCachedMap();
        DataInfo dataInfoSend = maps.get(StringUtils.getStringId(dataInfoReceived.getDataId(),dataInfoReceived.getGroup()));
        if (null == dataInfoSend) {
            ctx.channel().writeAndFlush(dataInfoSend);
        }
    }
}
