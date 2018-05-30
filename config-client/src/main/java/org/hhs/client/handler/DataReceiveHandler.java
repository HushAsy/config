package org.hhs.client.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hhs.ConfigService;
import org.hhs.listeners.ClientEventSource;
import org.hhs.model.DataInfo;
import org.hhs.model.MessageType;
import org.hhs.model.NettyMessage;

public class DataReceiveHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DataInfo dataInfo = (DataInfo) msg;
        ClientEventSource.getClientEventSource().updateMessage(dataInfo);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
