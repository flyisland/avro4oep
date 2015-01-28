package iptv.demo.kafka;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
        long events = 5;
        
        if (args.length == 1){
            events =  Long.parseLong(args[0]);
        }
        Random rnd = new Random();
        
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResourceAsStream("kafka.properties"));  
        ProducerConfig config = new ProducerConfig(props);
        
        Producer<String, String> producer = new Producer<String, String>(config);
        String json ="[{\"time\":\"20150127041043\", \"pageId\":1, \"area\":\"1\", \"pv\":2, \"uv\":1},\n" + 
        "{ \"time\":\"20150127041043\", \"pageId\":2, \"area\":\"1\", \"pv\":1, \"uv\":1},\n" + 
        "{ \"time\":\"20150127041043\", \"pageId\":4, \"area\":\"1\", \"pv\":2, \"uv\":1}]\n";        
        
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("browserStreamSec", json);
        producer.send(data);
        producer.close();
    }
}
