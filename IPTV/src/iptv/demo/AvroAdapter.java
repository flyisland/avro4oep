package iptv.demo;

import com.bea.wlevs.ede.api.RunnableBean;
import com.bea.wlevs.ede.api.StreamSender;
import com.bea.wlevs.ede.api.StreamSource;

import java.net.InetSocketAddress;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.flume.source.avro.AvroSourceProtocol;
import org.apache.flume.source.avro.AvroFlumeEvent;
import org.apache.flume.source.avro.Status;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;

public class AvroAdapter implements RunnableBean, StreamSource, AvroSourceProtocol {
    private static final int SLEEP_MILLIS = 1000;
    private Logger logger = LoggerFactory.getLogger(AvroAdapter.class);
    private StreamSender    eventSender;
    private int             bindPort = 1000;
    private String          bindAddr = "localhost";
    private boolean         suspended;
    private Server          avroServer;
    
    public static void main(String args[]) { 
        AvroAdapter adapter = new AvroAdapter();
        adapter.run();
    }

    public AvroAdapter() {
        super();
    }

    @Override
    public void run() {
        logger.info("Starting AvroAdapter on {}:{}", this.bindAddr, this.bindPort);
        avroServer = new NettyServer(new SpecificResponder(AvroSourceProtocol.class, this), new InetSocketAddress(bindAddr, bindPort));
        avroServer.start();
        try {
            avroServer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  
    @Override
    public synchronized void suspend() {
        avroServer.close();
        suspended = true;
    }

    private synchronized boolean isSuspended() {
        return suspended;
    }

    @Override
    public void setEventSender(StreamSender streamSender) {
        eventSender = streamSender;
    }
    
    public void setBindPort(String port){
        bindPort = Integer.parseInt(port);
    }

    public void setBindAddress(String addr){
        bindAddr = addr;
    }

    @Override
    public Status append(AvroFlumeEvent avroEvent) {
        String  message = new String(avroEvent.getBody().asCharBuffer().array());
        HelloWorldEvent event = new HelloWorldEvent();
        event.setMessage(message);
        eventSender.sendInsertEvent(event);

        return Status.OK;
    }

    @Override
    public Status appendBatch(List<AvroFlumeEvent> events) {
        for (AvroFlumeEvent avroEvent : events)
        {
            this.append(avroEvent);
        }
        return Status.OK;
    }
}
