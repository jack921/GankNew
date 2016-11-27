package com.gank.jack.ganknew.bean;

/**
 * Created by Jack on 2016/11/28.
 */

public class CollectGank extends BaseData{
    public boolean action;
    public Gank gank;

    public CollectGank(boolean action, Gank gank) {
        this.action = action;
        this.gank = gank;
    }
}
