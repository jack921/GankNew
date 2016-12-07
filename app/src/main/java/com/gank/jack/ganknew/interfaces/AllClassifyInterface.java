package com.gank.jack.ganknew.interfaces;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.gank.jack.ganknew.bean.Sort;

import java.util.List;

/**
 * @author 谢汉杰
 */

public interface AllClassifyInterface {
    public void initViewData(TabLayout tabLayout,List<Fragment> listFragment,List<String> listTitle);
}
