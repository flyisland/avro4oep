package iptv.demo;

import com.bea.wlevs.ede.api.BatchStreamSink;
import com.bea.wlevs.ede.api.EventRejectedException;

import java.util.Collection;
import java.util.Date;

public class BatchJSONBean implements BatchStreamSink {
    private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMddhhmmss"); 
    private String  etopic; // topic name of kafka

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
        System.out.println(json);
    }

    @Override
    public void onInsertEvent(Object object) throws EventRejectedException {     
        if (object instanceof PVUVEvent) {
            StringBuilder  json = new StringBuilder("[");
            String eTime = df.format(new Date());
            PVUVEvent pvuv = (PVUVEvent) object;
            json.append(toJson(eTime, pvuv)).append("]");
            System.out.println(json);
        }
    }
    
    private StringBuilder toJson(String eTime, PVUVEvent pvuv) {
        StringBuilder  json = new StringBuilder("{");
        json.append("\"type\":\"").append(etopic).append("\", \"time\":\"").append(eTime);
        json.append("\", \"pageId\":").append(pvuv.getPageId()).append(", \"area\":\"").append(pvuv.getAreaId());
        json.append("\", \"pv\":").append(pvuv.getPv()).append(", \"uv\":").append(pvuv.getUv()).append("}");
        return json;
    }
}
