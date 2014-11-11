package iptv.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.nio.charset.Charset;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

public class TestLoader {
    private static final int SLEEP_MILLIS = 1000;

    public TestLoader() {
        super();
    }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    public static void main(String[] args) throws IOException, EventDeliveryException {
        String str_line = null;
        BufferedReader br = new BufferedReader(new FileReader("r:\\user_iptv.txt"));
        RpcClient client =  RpcClientFactory.getDefaultInstance("10.10.10.10", 9090);

        while(null != (str_line = br.readLine())){
            client.append(EventBuilder.withBody(str_line, Charset.forName("UTF-8")));
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.close();
    }
}
