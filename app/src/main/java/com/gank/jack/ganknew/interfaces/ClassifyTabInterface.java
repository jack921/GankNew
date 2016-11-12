package com.gank.jack.ganknew.interfaces;

import com.gank.jack.ganknew.bean.Gank;

import java.util.List;

/**
 * Created by Jack on 2016/11/11.
 */

public interface ClassifyTabInterface extends BaseInterface{

    public void getTypeGank(List<Gank> listGank,boolean isLast);

    public void error(Throwable throwable);

}
