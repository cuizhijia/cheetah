package com.cheetah.gateway.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class DemoApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof DemoEvent demoEvent) {
            System.out.println(demoEvent.getSource());
        }
        System.out.println("linstener 初始化。。。"+event.getClass());
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
