package org.hhs.configserver.common;

public class Enviroment {

    private String host;
    private int port;

    public Enviroment(){
    }

    public Enviroment(Builder builder){
        this.host = builder.host;
        this.port = builder.port;
    }

    public static class Builder{
        private String host;
        private int port;

        public Builder setHost(String host){
            this.host = host;
            return this;
        }

        public Builder setPort(int port){
            this.port = port;
            return this;
        }

        public Enviroment newInstance(){
            return new Enviroment(this);
        }

    }
}
