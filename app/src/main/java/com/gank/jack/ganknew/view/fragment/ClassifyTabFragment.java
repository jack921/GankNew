package com.gank.jack.ganknew.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gank.jack.ganknew.R;
import com.gank.jack.ganknew.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/11/10.
 */

public class ClassifyTabFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classifgtab,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

}
