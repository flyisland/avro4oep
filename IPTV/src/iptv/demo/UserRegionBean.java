package iptv.demo;

import java.io.Serializable;

public class UserRegionBean implements Serializable {
    @SuppressWarnings("compatibility:-9013833204187906798")
    private static final long serialVersionUID = 1L;
    private String  userId;
    private String  regionId;

    public static UserRegionBean newInstance(String uid, String rid){
        UserRegionBean urb = new UserRegionBean();
        urb.setUserId(uid);
        urb.setRegionId(rid);
        return urb;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionId() {
        return regionId;
    }

    public UserRegionBean() {
        super();
    }
    
    public String toString(){
        return userId+"->"+regionId;
    }
}
