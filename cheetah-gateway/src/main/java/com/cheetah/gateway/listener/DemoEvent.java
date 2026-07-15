package com.cheetah.gateway.listener;


import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class DemoEvent extends ApplicationContextEvent {


    public DemoEvent(ApplicationContext source) {
        super(source);
    }
}
