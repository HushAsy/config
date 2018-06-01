package org.hhs;

import org.hhs.listeners.ConfigChangeListener;

import java.io.IOException;

public class Main {

    public static void main(String...args) throws IOException {
        ConfigService.addListener("hello1", "test", new ConfigChangeListener() {
            @Override
            public void receiveConfigInfo(String content) {
                System.out.println(content);
            }
        });
        System.in.read();
    }
}
