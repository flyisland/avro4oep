package iptv.demo;

import com.bea.wlevs.ede.api.RunnableBean;
import com.bea.wlevs.ede.api.StreamSender;
import com.bea.wlevs.ede.api.StreamSource;

import java.text.DateFormat;

import java.util.Date;

public class AvroAdapter implements RunnableBean, StreamSource {
    private static final int SLEEP_MILLIS = 10000;

    private DateFormat      dateFormat;
    private StreamSender    eventSender;
    private int             listenPort = 1000;
    private boolean         suspended;


    public AvroAdapter() {
        super();
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT);       
    }

    @Override
    public void run() {
        suspended = false;
        while (!isSuspended()) { // Generate messages forever...

            generateHelloMessage();

            try {
                synchronized (this) {
                    wait(SLEEP_MILLIS);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateHelloMessage() {
        String message = dateFormat.format(new Date()) + ": the port is -> "+this.listenPort;
        HelloWorldEvent event = new HelloWorldEvent();
        event.setMessage(message);
        eventSender.sendInsertEvent(event);
    }
    
    @Override
    public synchronized void suspend() {
        suspended = true;
    }

    private synchronized boolean isSuspended() {
        return suspended;
    }

    @Override
    public void setEventSender(StreamSender streamSender) {
        eventSender = streamSender;
    }
    
    public void setPort(String port){
        listenPort = Integer.parseInt(port);
    }
}
