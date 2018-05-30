package org.hhs.model;

import java.io.Serializable;

//配置推送的信息:dataId+group确定一条记录
public class DataInfo implements Serializable{
    private String remoteIp;
    private String dataId;
    private String content;
    private String group;

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "DataInfo{" +
                "remoteIp='" + remoteIp + '\'' +
                ", dataId='" + dataId + '\'' +
                ", content='" + content + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
