package org.hhs;

import static org.junit.Assert.assertTrue;

import io.netty.channel.ChannelFuture;
import org.hhs.client.NettyClient;
import org.hhs.model.Header;
import org.hhs.model.MessageType;
import org.hhs.model.NettyMessage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private NettyClient nettyClient;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Before
    public void testClient() throws InterruptedException {
        nettyClient = new NettyClient();
        nettyClient.connect("127.0.0.1", 8000);
    }

    @Test
    public void testSendData() throws IOException {
        ChannelFuture channelFuture = nettyClient.getChannelFuture();
        channelFuture.channel().writeAndFlush(buildNettyMessage());
        System.in.read();
    }

    private NettyMessage buildNettyMessage(){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header.Builder().addType(MessageType.DATA_PUSH.getaByte()).addSessionId(StringUtils.getUUID32()).build();
        nettyMessage.setHeader(header);
        nettyMessage.setBody("Hello World!");
        return nettyMessage;
    }
}
