package com.gank.jack.ganknew.adapter;

import com.gank.jack.ganknew.view.fragment.PhotoFragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;
import com.gank.jack.ganknew.bean.Gank;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import java.util.List;

/**
 * @author 谢汉杰
 */

public class PhotoFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Gank> listGank=null;
    private int current;

    public PhotoFragmentAdapter(FragmentManager fm,List<Gank> listGank,int current) {
        super(fm);
        this.listGank=listGank;
        this.current=current;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putString("url",listGank.get(position).url);
        bundle.putString("id",listGank.get(position)._id);
        bundle.putBoolean("current",current==position);
        PhotoFragment photoFragment=new PhotoFragment();
        photoFragment.setArguments(bundle);
        return photoFragment;
    }

    @Override
    public int getCount() {
        return listGank.size();
    }

}
