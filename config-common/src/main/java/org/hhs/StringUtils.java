package org.hhs;

import java.util.UUID;

public class StringUtils {

    //得到32位的uuid
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String getStringId(String...args){
        StringBuilder stringBuilder = new StringBuilder();
        for(String str:args){
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
