package org.hhs;

public class EnviromentInfo {
    private static String ip;
    private static int port;

    public static String getIp(){
        String string = System.getProperty("config.server.ip");
        return null;
    }


}
