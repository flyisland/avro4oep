package iptv.demo;

public class AlertEvent {
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
        return String.format("ID: %s, Value: %d", id, figure);
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public int getFigure() {
        return figure;
    }
}
