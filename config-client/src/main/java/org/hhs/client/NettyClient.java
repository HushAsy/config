package org.hhs.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.hhs.ConfigService;
import org.hhs.EnviromentInfo;
import org.hhs.client.handler.DataReceiveHandler;

import java.util.concurrent.CountDownLatch;

public class NettyClient {
    private static NettyClient nettyClient = null;

    public static NettyClient getNettyClient(){
        if (nettyClient == null){
            synchronized (ConfigService.class){
                if (nettyClient == null){
                    nettyClient = new NettyClient();
                    return nettyClient;
                }
            }
        }
        return nettyClient;
    }

    private EventLoopGroup group = new NioEventLoopGroup();
    private static ChannelFuture channelFuture;



    public void connect() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.TCP_NODELAY,true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel ch) throws Exception {
                         ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(Thread.currentThread().getContextClassLoader())));
                         ch.pipeline().addLast(new ObjectEncoder());
                         ch.pipeline().addLast(new DataReceiveHandler());
                     }
                 });
        try {
            channelFuture = bootstrap.connect(EnviromentInfo.getEnviromentInfo().getIp(), Integer.parseInt(EnviromentInfo.getEnviromentInfo().getPort())).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }
}
