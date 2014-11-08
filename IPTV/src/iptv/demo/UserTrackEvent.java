package iptv.demo;

import java.sql.Date;

public class UserTrackEvent {
    private Date        trackTime;
    private String      userId;
    private String      targetURL;
    private String      referURL;
    private int         pageId;
    
    public UserTrackEvent() {
        super();
    }
}
