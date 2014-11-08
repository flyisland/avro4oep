package iptv.demo;

import com.bea.wlevs.ede.api.StreamSink;

public class HelloWorldBean implements StreamSink {

    public void onInsertEvent(Object event) {
        if (event instanceof HelloWorldEvent) {
            HelloWorldEvent helloWorldEvent = (HelloWorldEvent) event;
            System.out.println("Hello Message: " + helloWorldEvent.getMessage());
        }
    }

}
