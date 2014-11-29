package iptv.demo.kafka;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
public class KafkaTest {
    public KafkaTest() {
        super();
    }

    public static void main(String[] args) {
        long events = 5;
        
        if (args.length == 1){
            events =  Long.parseLong(args[0]);
        }
        Random rnd = new Random();
        
        Properties props = new Properties();
        props.put("metadata.broker.list", "10.10.10.10:9092,10.10.10.10:9093");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "iptv.demo.kafka.SimplePartitioner");
        props.put("request.required.acks", "1");
        
        ProducerConfig config = new ProducerConfig(props);
        
        Producer<String, String> producer = new Producer<String, String>(config);
        
        for (long nEvents = 0; nEvents < events; nEvents++) { 
               long runtime = new Date().getTime();  
               String ip = "192.168.2." + rnd.nextInt(255); 
               String msg = runtime + "www.example.com" + ip; 
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
            producer.send(data);
        }
        producer.close();
    }
}