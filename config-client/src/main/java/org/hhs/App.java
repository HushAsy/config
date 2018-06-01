package org.hhs;

import org.hhs.client.NettyClient;
import org.hhs.listeners.ConfigChangeListener;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws InterruptedException, IOException {

        ConfigService.addListener("hello", "test", new ConfigChangeListener() {
            @Override
            public void receiveConfigInfo(String content) {
                System.out.println(content);
            }
        });
        System.in.read();
//        EnviromentInfo info = new EnviromentInfo();
//        System.out.println(info.getIp());
//        System.out.println(info.getPort());
    }
}
