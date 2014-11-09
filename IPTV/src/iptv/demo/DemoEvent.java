package iptv.demo;

public class DemoEvent {
    private String      trackTime;
    private String      userId;
    private String      targetURL;
    private String      referURL;
    private String      pageId;

    public static DemoEvent newInstance(String[] list){
        DemoEvent de = new DemoEvent();
        try {
            de.setTrackTime(list[0]);
            de.setUserId(list[1]);
            de.setTargetURL(list[2]);
            de.setReferURL(list[3]);
            de.setPageId(list[4]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return de;
    }

    public void setTrackTime(String trackTime) {
        this.trackTime = trackTime;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setReferURL(String referURL) {
        this.referURL = referURL;
    }

    public String getReferURL() {
        return referURL;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public DemoEvent() {
        super();
    }
    
    public String toString(){
        return trackTime+", "+userId+", "+targetURL+", "+referURL+", "+pageId;
    }
}
