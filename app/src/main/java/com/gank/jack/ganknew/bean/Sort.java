package com.gank.jack.ganknew.bean;

/**
 * Created by Jack on 2016/11/15.
 */

public class Sort{
    public Sort(){}
    public Sort(String title, boolean classify, boolean more, boolean normal) {
        this.title = title;
        this.classify = classify;
        this.more = more;
        this.normal = normal;
    }
    public String title;
    public boolean classify;
    public boolean more;
    public boolean normal;
}
