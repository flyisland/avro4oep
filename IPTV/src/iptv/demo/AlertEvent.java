package iptv.demo;

public class AlertEvent {
    private String  atype;

    public void setAtype(String type) {
        this.atype = type;
    }

    public String getAtype() {
        return atype;
    }
    private String  id;
    private int  figure;
    public AlertEvent() {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    
    public String toString(){
        return String.format("Type: %s, ID: %s, Value: %d", atype, id, figure);
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public int getFigure() {
        return figure;
    }
}
