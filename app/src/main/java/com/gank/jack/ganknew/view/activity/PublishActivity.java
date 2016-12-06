package com.gank.jack.ganknew.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.adapter.PublishSpinnerAdapter;
import com.gank.jack.ganknew.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 谢汉杰
 */

public class PublishActivity extends BaseActivity{

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

    public PublishSpinnerAdapter adapter;
    public List<String> listSpinner;

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
        listSpinner=new ArrayList<>();
        String[] param=getResources().getStringArray(R.array.type);
        for(int i=0;i<param.length;i++){
            listSpinner.add(param[i]);
        }
//        adapter=new PublishSpinnerAdapter(this,listSpinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,param);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        publishSpinner.setAdapter(adapter);
    }


}
