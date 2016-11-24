package com.gank.jack.ganknew.interfaces;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.gank.jack.ganknew.bean.Sort;

import java.util.List;

/**
 * 类名称：AllClassifyInterface.java <br>
 * 内容摘要：首页
 * 属性描述：<br>
 * 方法描述：<br>
 * 修改备注： <br>
 * 创建时间： 2016/11/24 14:22<br>
 * 公司：深圳市华移科技股份有限公司<br>
 *
 * @author 谢汉杰
 */

public interface AllClassifyInterface {

    public void initViewData(TabLayout tabLayout,List<Fragment> listFragment);

    public void updateTabClassify(boolean result);

}
