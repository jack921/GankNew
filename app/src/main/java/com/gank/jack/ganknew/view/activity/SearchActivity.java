package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名称：SearchActivity.java <br>
 * 内容摘要：首页
 * 属性描述：<br>
 * 方法描述：<br>
 * 修改备注： <br>
 * 创建时间： 2016/12/5 15:21<br>
 * 公司：深圳市华移科技股份有限公司<br>
 *
 * @author 谢汉杰
 */

public class SearchActivity extends BaseActivity implements TextView.OnEditorActionListener {

    @Bind(R.id.search_toolbar)
    public Toolbar searchToolbar;
    @Bind(R.id.textInputLayout)
    public TextInputLayout searchInputLayout;
    @Bind(R.id.search_edittext)
    public EditText searchEdittext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchEdittext.setOnEditorActionListener(this);
        searchEdittext.clearFocus();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId== EditorInfo.IME_ACTION_SEARCH ||
             (keyEvent!=null&&keyEvent.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
            searchInputLayout.setHint("加载中");

        }
        return false;
    }

    @OnClick(R.id.search_back)
    public void onClick(View v){
        switch(v.getId()){
            case R.id.search_back:
                finish();
                break;
        }
    }

}
