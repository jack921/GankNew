package com.gank.jack.ganknew.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.gank.jack.ganknew.utils.PictureUtils;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jack on 2016/12/18.
 */

public class PhotoPersenter extends BasePresenter{

    public PhotoPersenter(Context context) {
        super(context);
    }

    public void saveImage(Context context,String url){
        try {
            PictureUtils.saveBitmapFromUrl(context,url);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sharedImage(Context context,String title,String msg,String url){
        String imgPath = PictureUtils.getImgPathFromUrl(url);

        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain");
        } else {
            File file = new File(imgPath);
            if (!file.exists()) {
                try {
                    PictureUtils.saveBitmapFromUrl(context, url);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (file.exists() && file.isFile()) {
                intent.setType("image/jpg");
                Uri uri = Uri.fromFile(file);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
