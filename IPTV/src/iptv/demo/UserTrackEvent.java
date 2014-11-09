package iptv.demo;

import java.sql.Date;

public class UserTrackEvent {
    private Date        trackTime;
    private String      userId;
    private String      targetURL;
    private String      referURL;
    private String         pageId;
    
    public UserTrackEvent() {
        super();
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public Date getTrackTime() {
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
}
