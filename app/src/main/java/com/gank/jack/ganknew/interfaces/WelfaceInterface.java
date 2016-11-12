package com.gank.jack.ganknew.interfaces;

import com.gank.jack.ganknew.bean.Gank;
import java.util.List;

/**
 * Created by Jack on 2016/11/12.
 */

public interface WelfaceInterface extends BaseInterface{
     public void getWelfaceData(List<Gank> listGank,boolean isLast);
     public void error(Throwable e);
}
