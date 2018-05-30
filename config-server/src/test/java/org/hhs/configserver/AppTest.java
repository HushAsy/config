package org.hhs.configserver;

import org.hhs.configserver.server.NettyServer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AppTest {
    private NettyServer nettyServer;

    @Before
    public void init() throws InterruptedException {
        nettyServer = new NettyServer();
        nettyServer.bind();
    }

    @Test
    public void test() throws IOException {
        System.in.read();
    }

}
