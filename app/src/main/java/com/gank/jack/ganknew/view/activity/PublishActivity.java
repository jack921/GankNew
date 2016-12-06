package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseActivity;
import com.gank.jack.ganknew.bean.PublishResult;
import com.gank.jack.ganknew.interfaces.PublishInterface;
import com.gank.jack.ganknew.presenter.PublishPersenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 谢汉杰
 */

public class PublishActivity extends BaseActivity implements PublishInterface{

    @Bind(R.id.publish_toolbar)
    public Toolbar publishToolbar;
    @Bind(R.id.commit_url)
    public EditText commitUrl;
    @Bind(R.id.commit_content)
    public EditText commitContent;
    @Bind(R.id.commit_id)
    public EditText commitId;
    @Bind(R.id.publishSpinner)
    public AppCompatSpinner publishSpinner;

    public PublishPersenter publishPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        publishToolbar.setTitle(getString(R.string.publish));
        setBaseSupportActionBar(publishToolbar);
        init();
    }

    public void init(){
        publishPersenter=new PublishPersenter(this);
        String[] param=getResources().getStringArray(R.array.type);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,param);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        publishSpinner.setAdapter(adapter);
    }

    @OnClick(R.id.publicshfinish)
    public void onClick(View v){
        switch(v.getId()){
            case R.id.publicshfinish:
                publishPersenter.publishData(this,commitUrl.getText().toString(),commitContent.getText().toString()
                        ,commitId.getText().toString(),(String)publishSpinner.getSelectedItem());
                break;
        }
    }

    @Override
    public void publishResult(PublishResult publishResult) {
        if(publishResult.error==false){
            finish();
        }else{
            showSnackbar(publishResult.msg);
        }
    }

    @Override
    public void error(Throwable e) {
        showSnackbar("操作失败了>-<");
    }


}
