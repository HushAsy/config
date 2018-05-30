package org.hhs.configserver.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.hhs.configserver.common.ChannelHolder;

public class ConnectHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ChannelHolder.putChannel(ctx.channel());
        System.out.println(ctx.channel().toString()+"has connect");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ChannelHolder.removeChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ChannelHolder.removeChannel(ctx.channel());
        System.out.println(ctx.channel().toString()+" connect close");
    }
}
