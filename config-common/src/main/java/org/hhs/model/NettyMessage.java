package org.hhs.model;

import java.io.Serializable;

/**
 * Created by 3307 on 2016/3/4.
 */
public final class NettyMessage implements Serializable{
    private static final long serialVersionUID = 1L;
    private Header header;
    private Object body;

    public NettyMessage(){

    }

    public NettyMessage(Builder builder){
        this.header = builder.header;
        this.body = builder.object;
    }

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    public static class Builder{
        Header header;
        Object object;

        public Builder addHeader(Header header){
            this.header = header;
            return this;
        }

        public Builder addObject(Object obj){
            this.object = obj;
            return this;
        }

        public NettyMessage build(){
            return new NettyMessage(this);
        }
    }
}
