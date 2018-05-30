package org.hhs.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 3307 on 2016/3/4.
 */
public final class Header implements Serializable{
    private static final long serialVersionUID = 1L;

    //消息验证码 3部分组成
    //0xABEF：固定值，表示是Netty协议消息，2个字节
    //主版本号：1~255 1个字节
    //次版本号：1~255 1个字节
    private int crcCode = 0xabef0101;
    //消息长度
    private int length;
    //记录此次请求的id号
    private String sessionID;
    //类型
    private byte type;
    //消息优先级 0-255
    private byte priority;
    //可选字段，用户扩展消息头
    private Map<String,Object> attachment = new HashMap<String, Object>();

    private Header(){
    }

    public Header(Builder builder){
        this.crcCode = builder.crcCode;
        this.length = builder.length;
        this.sessionID = builder.sessionID;
        this.type = builder.type;
        this.priority = builder.priority;
        this.attachment = builder.attachment;
    }


    public final int getCrcCode() {
        return crcCode;
    }

    public final Header setCrcCode(int crcCode) {
        this.crcCode = crcCode;
        return this;
    }

    public final int getLength() {
        return length;
    }

    public final Header setLength(int length) {
        this.length = length;
        return this;
    }

    public final String getSessionID() {
        return sessionID;
    }

    public final Header setSessionID(String sessionID) {
        this.sessionID = sessionID;
        return this;
    }

    public final byte getType() {
        return type;
    }

    public final Header setType(byte type) {
        this.type = type;
        return this;
    }

    public final byte getPriority() {
        return priority;
    }

    public final void setPriority(byte priority) {
        this.priority = priority;
    }

    public final Map<String, Object> getAttachment() {
        return attachment;
    }

    public final void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }

    public static class Builder {
        private int crcCode = 0xabef0101;
        //消息长度
        private int length;
        private String sessionID;
        //类型
        private byte type;
        //消息优先级 0-255
        private byte priority;
        //可选字段，用户扩展消息头
        private Map<String,Object> attachment = new HashMap<String, Object>();

        public Builder addCrcCode(int crcCode){
            this.crcCode = crcCode;
            return this;
        }

        public Builder addLength(int length){
            this.length = length;
            return this;
        }

        public Builder addSessionId(String sessionId){
            this.sessionID = sessionId;
            return this;
        }

        public Builder addType(byte b){
            this.type = b;
            return this;
        }

        public Builder addPriority(byte priority){
            this.priority = priority;
            return this;
        }

        public Builder addAttachments(Map<String, Object> map){
            this.attachment = map;
            return this;
        }

        public Header build(){
            return new Header(this);
        }
    }
}
