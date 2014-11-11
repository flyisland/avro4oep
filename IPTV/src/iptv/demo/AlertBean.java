package iptv.demo;

import com.bea.wlevs.ede.api.EventRejectedException;
import com.bea.wlevs.ede.api.StreamSink;
import java.util.Date; 

public class AlertBean implements StreamSink {
    java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss"); 
    public AlertBean() {
        super();
    }

    @Override
    public void onInsertEvent(Object event) throws EventRejectedException {
        if (event instanceof AlertEvent) {
            AlertEvent alert = (AlertEvent) event;
            
            System.out.println(df.format(new Date())+"->"+alert.toString());
        }
    }
}
