package com.gank.jack.ganknew.bean;

/**
 * Created by Jack on 2016/11/4.
 */

public class Gank {
    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;

    public boolean isHeader;

    public boolean isMeizi(){
        return type.equals("福利");
    }

    @Override
    public Gank clone() {
        Gank gank = null;
        try{
            gank = (Gank)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return gank;
    }
}
