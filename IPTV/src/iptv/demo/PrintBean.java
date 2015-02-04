package iptv.demo;

import com.bea.wlevs.ede.api.BatchStreamSink;
import com.bea.wlevs.ede.api.EventRejectedException;

import java.util.Collection;
import java.util.Date; 

public class PrintBean implements BatchStreamSink {
    java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss"); 

    public PrintBean() {
        super();
    }

    @Override
    public void onInsertEvents(Collection<Object> collection) throws EventRejectedException {
        System.out.println("=== Batch  ---> "+df.format(new Date()));
        for (Object object : collection){
            System.out.println(object.toString());
        }
    }

    @Override
    public void onInsertEvent(Object object) throws EventRejectedException {
        System.out.println("=== Single ---> "+df.format(new Date()));
        System.out.println(object.toString());
    }
}
