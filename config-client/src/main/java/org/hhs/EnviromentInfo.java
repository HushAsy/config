package org.hhs;

import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: hewater
 * @create: 2018-05-31 21:16
 **/
public class EnviromentInfo {
    private static EnviromentInfo enviromentInfo;
    public static EnviromentInfo getEnviromentInfo(){
        if (enviromentInfo == null){
            synchronized (EnviromentInfo.class){
                if(enviromentInfo == null){
                    enviromentInfo = new EnviromentInfo();
                    return enviromentInfo;
                }
            }
        }
        return enviromentInfo;
    }

    //config.server.ip指定ip地址;config.server.port指定port地址，可以通过-D参数指定;在config.properties或application.properties文件中指定

    private Properties getProperties(){
        InputStream inputStream = EnviromentInfo.class.getClassLoader().getResourceAsStream("config.properties");
        if (inputStream != null) {
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return properties;
        }
        return null;
    }

    public String getIp(){
        //从vm args -D参数中取值
        String ip = System.getProperty("config.server.ip");
        if (null != ip) {
            if (isIP(ip)) {
                return ip;
            }
        }
        //从config.properties中取值
        Properties p = getProperties();
        ip = p.getProperty("config.server.ip");
        if (isIP(ip)){
            return ip;
        }
        throw new RuntimeException("please set the server ip");
    }

    public String getPort(){
        //从vm args -D参数中取值
        String port = System.getProperty("config.server.port");
        if (null != port) {
            if (isNumeric(port)) {
                return port;
            }
        }
        //从config.properties中取值
        Properties p = getProperties();
        if (null != p) {
            port = p.getProperty("config.server.port");
            if (null != port) {
                if (isNumeric(port)) {
                    return port;
                }
            }
        }
        throw new RuntimeException("please set the server port");
    }

    private  boolean isIP(String str) {
        String regex = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
