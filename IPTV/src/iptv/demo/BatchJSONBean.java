package iptv.demo;

import com.bea.wlevs.ede.api.BatchStreamSink;
import com.bea.wlevs.ede.api.EventRejectedException;

import java.util.Collection;
import java.util.Date;

public class BatchJSONBean implements BatchStreamSink {
    java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddhhmmss"); 
    public BatchJSONBean() {
        super();
    }

    @Override
    public void onInsertEvents(Collection<Object> collection) throws EventRejectedException {
        System.out.println("  ==B==   ");
        
        String  json;
        String eTime = df.format(new Date());
        for (Object obj : collection){
            if (obj instanceof PVUVEvent) {
                PVUVEvent pvuv = (PVUVEvent) obj;
                json= String.format("{\"type\":\"%s\", \"time\":\"%s\", \"pageId\":%d, \"area\":\"%s\", \"pv\":%d, \"uv\":%d, }",
                                    pvuv.getEtype(), eTime, pvuv.getPageId(), pvuv.getAreaId(), pvuv.getPv(), pvuv.getUv());
                System.out.println(json);
            }
        }
    }

    @Override
    public void onInsertEvent(Object object) throws EventRejectedException {
        System.out.println("  ==S==   ");
        
        String  json;
        String eTime = df.format(new Date());
        if (object instanceof PVUVEvent) {
            PVUVEvent pvuv = (PVUVEvent) object;
            json= String.format("{\"type\":\"%s\", \"time\":\"%s\", \"pageId\":\"%s\", \"areaId\":\"%s\", \"pv\":\"%d\", \"uv\":\"%d\", }",
                                pvuv.getEtype(), eTime, pvuv.getPageId(), pvuv.getAreaId(), pvuv.getPv(), pvuv.getUv());
            System.out.println(json);
        }
    }
}
