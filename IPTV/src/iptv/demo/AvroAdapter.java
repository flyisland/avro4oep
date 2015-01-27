package iptv.demo;

import au.com.bytecode.opencsv.CSVReader;

import com.bea.wlevs.ede.api.RunnableBean;
import com.bea.wlevs.ede.api.StreamSender;
import com.bea.wlevs.ede.api.StreamSource;

import java.io.IOException;
import java.io.StringReader;

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
    private Logger logger = LoggerFactory.getLogger(AvroAdapter.class);
    private StreamSender    eventSender;
    private int             bindPort = 1000;
    private String          bindAddr = null;
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
        InetSocketAddress isaddr;
        if ((null == bindAddr) || (bindAddr.length() == 0)) {
            isaddr = new InetSocketAddress(bindPort);
        } else {
            isaddr = new InetSocketAddress(bindAddr, bindPort);
        }
        avroServer = new NettyServer(new SpecificResponder(AvroSourceProtocol.class, this), isaddr );
        avroServer.start();
        logger.info("Starting AvroAdapter on {}", isaddr.toString());
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
        String  line = new String(avroEvent.getBody().array());      
        CSVReader csv = new CSVReader(new StringReader(line), '\t');
        String[] list = null;
        try {
            list = csv.readNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RawEvent de = RawEvent.newInstance(list);
        if (null != de){
            this.eventSender.sendInsertEvent(de);
        }
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
