package org.hhs.configserver.EventListener;

public interface EventListener<T> {
    public void eventHandler(T t);
}
