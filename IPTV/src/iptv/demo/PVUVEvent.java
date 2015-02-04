package iptv.demo;

public class PVUVEvent extends RawEvent {
    private int  pv;
    private int  uv;

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

    public String toString(){
        return super.toString()+", pv:"+this.pv+", uv:"+this.uv;
    }
}
