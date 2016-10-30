package com.gank.jack.ganknew.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gank.jack.ganknew.R;

import butterknife.ButterKnife;

/**
 * Created by Jack on 2016/10/31.
 */

public class MyCollectFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect,container,false);
        ButterKnife.bind(this,view);
        return view;
    }


}
