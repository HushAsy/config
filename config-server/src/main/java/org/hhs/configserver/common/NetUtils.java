package org.hhs.configserver.common;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetUtils {

    public static List<String> getNetWorkAddress(){
        List<String> result = new ArrayList<>();
        Enumeration<NetworkInterface> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()){
                    ip = address.nextElement();
                    if (ip.isLoopbackAddress()&&ip.getHostAddress().indexOf(':') == -1){
                        result.add(ip.getHostAddress());
                    }
                }
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }

    public String getClientId(Channel channel){
        return channel.id().asLongText();
    }
}
