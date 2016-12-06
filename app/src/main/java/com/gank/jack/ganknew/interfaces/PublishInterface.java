package com.gank.jack.ganknew.interfaces;

import com.gank.jack.ganknew.bean.PublishResult;

/**
 * Created by Jack on 2016/12/7.
 */

public interface PublishInterface {

    public void publishResult(PublishResult publishResult);

    public void error(Throwable e);

}
