package iptv.demo;

import com.bea.wlevs.ede.api.BatchStreamSink;
import com.bea.wlevs.ede.api.EventRejectedException;

import java.io.IOException;

import java.util.Collection;
import java.util.Date;

import java.util.Properties;

import kafka.javaapi.producer.Producer;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class BatchJSONBean implements BatchStreamSink {
    private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddhhmmss"); 
    private String  etopic; // topic name of kafka
    private Producer<String, String> producer = null;

    public void init() {
        if (producer == null){
            Properties props = new Properties();
            props.setProperty("metadata.broker.list", "itv-7:9092,itv-8:9092,itv-9:9092");
            props.setProperty("producer.type", "async");
            props.setProperty("compression.codec", "none");
            props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
            props.setProperty("batch.num.messages", "100");
            ProducerConfig config = new ProducerConfig(props);
            producer = new Producer<String, String>(config);
        }
    }

    public void setEtopic(String etopic) {
        this.etopic = etopic;
    }

    public String getEtopic() {
        return etopic;
    }

    public BatchJSONBean() {
        super();
    }

    @Override
    public void onInsertEvents(Collection<Object> collection) throws EventRejectedException {
        StringBuilder  json = new StringBuilder("[");
        String eTime = df.format(new Date());
        boolean first = true;
        for (Object obj : collection){
            if (obj instanceof PVUVEvent) {
                PVUVEvent pvuv = (PVUVEvent) obj;
                if (!first) {json.append(",\n");}
                json.append(toJson(eTime, pvuv));
                first = false;
            }
        }
        json.append("]");
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(etopic, json.toString());
        init();
        producer.send(data);
//        System.out.println(json);
    }

    @Override
    public void onInsertEvent(Object object) throws EventRejectedException {     
        if (object instanceof PVUVEvent) {
            StringBuilder  json = new StringBuilder("[");
            String eTime = df.format(new Date());
            PVUVEvent pvuv = (PVUVEvent) object;
            json.append(toJson(eTime, pvuv)).append("]");
            KeyedMessage<String, String> data = new KeyedMessage<String, String>(etopic, json.toString());
            init();
            producer.send(data);
//          System.out.println(json);
        }
    }
    
    private StringBuilder toJson(String eTime, PVUVEvent pvuv) {
        StringBuilder  json = new StringBuilder("{");
        json.append("\"time\":\"").append(eTime);
        json.append("\", \"pageId\":").append(pvuv.getPageId()).append(", \"area\":\"").append(pvuv.getAreaId());
        json.append("\", \"pv\":").append(pvuv.getPv()).append(", \"uv\":").append(pvuv.getUv()).append("}");
        return json;
    }
}
