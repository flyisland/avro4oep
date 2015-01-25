package iptv.demo;

public class DemoEvent {
    private String      userId;
    private String      pageId;
    private String      areaId;

    public static DemoEvent newInstance(String[] list){
        DemoEvent de = new DemoEvent();
        try {
            de.setUserId(list[1]);
            de.setPageId(list[4]);
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

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public DemoEvent() {
        super();
    }
    
    public String toString(){
        return "uid: "+userId+", pid: "+pageId +", aid: "+areaId;
    }
}
