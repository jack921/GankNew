package com.gank.jack.ganknew.interfaces;

import com.gank.jack.ganknew.bean.SelectDate;

/**
 * 类名称：SelectDateInterface.java <br>
 * 内容摘要：首页
 * 属性描述：<br>
 * 方法描述：<br>
 * 修改备注： <br>
 * 创建时间： 2016/12/3 17:56<br>
 * 公司：深圳市华移科技股份有限公司<br>
 *
 * @author 谢汉杰
 */

public interface SelectDateInterface {
    public void getSelectDate(SelectDate selectDate);
    public void onError(Throwable e);
}
