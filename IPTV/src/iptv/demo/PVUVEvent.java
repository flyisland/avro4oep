package iptv.demo;

public class PVUVEvent {
    private String  etype;
    private int     pageId;
    private String  areaId;
    private int  pv;
    private int  uv;

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public String getEtype() {
        return etype;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPv() {
        return pv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getUv() {
        return uv;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }


    public String toString(){
        return "pageId:"+this.pageId+", area:"+this.areaId+", pv:"+this.pv+", uv:"+this.uv;
    }
}
