package org.hhs.configserver.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.hhs.StringUtils;
import org.hhs.model.Header;
import org.hhs.model.MessageType;
import org.hhs.model.NettyMessage;

public class DataReceiveHandler extends ChannelHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        NettyMessage message = (NettyMessage) msg;
        if (MessageType.DATA_PUSH.getaByte() == message.getHeader().getType()){
            System.out.println(message.toString());
        }
        ctx.channel().writeAndFlush(buildNettyMessage());
    }

    public NettyMessage buildNettyMessage(){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header.Builder().addType(MessageType.DATA_PULL.getaByte()).addSessionId(StringUtils.getUUID32()).build();
        nettyMessage.setHeader(header);
        nettyMessage.setBody("hello");
        return nettyMessage;
    }
}
