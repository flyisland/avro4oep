package iptv.demo;

public class RawEvent {
    private String      userId;
    private int         pageId;
    private String      areaId;

    public static RawEvent newInstance(String[] list){
        RawEvent de = new RawEvent();
        try {
            de.setUserId(list[1]);
            de.setPageId(Integer.parseInt(list[4]));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return de;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public RawEvent() {
        super();
    }
    
    public String toString(){
        return "uid: "+userId+", pid: "+pageId +", aid: "+areaId;
    }
}
