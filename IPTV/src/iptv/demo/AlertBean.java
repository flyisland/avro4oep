package iptv.demo;

import com.bea.wlevs.ede.api.EventRejectedException;
import com.bea.wlevs.ede.api.StreamSink;

public class AlertBean implements StreamSink {
    public AlertBean() {
        super();
    }

    @Override
    public void onInsertEvent(Object event) throws EventRejectedException {
        if (event instanceof AlertEvent) {
            AlertEvent alert = (AlertEvent) event;
            System.out.println(alert.toString());
        }
    }
}
