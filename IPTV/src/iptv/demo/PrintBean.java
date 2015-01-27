package iptv.demo;

import com.bea.wlevs.ede.api.EventRejectedException;
import com.bea.wlevs.ede.api.StreamSink;
import java.util.Date; 

public class PrintBean implements StreamSink {
    java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss"); 
    public PrintBean() {
        super();
    }

    @Override
    public void onInsertEvent(Object event) throws EventRejectedException {
        System.out.println(df.format(new Date())+"->"+event.toString());
    }
}
