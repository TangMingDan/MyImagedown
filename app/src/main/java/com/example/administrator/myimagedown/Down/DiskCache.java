package com.example.administrator.myimagedown.Down;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.administrator.myimagedown.Util.CloseUtil;

import java.io.FileOutputStream;

public class DiskCache implements ImageCache{
    static String cacheDir = "sdcard/cache";
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseUtil.closeQuietly(out);
        }
    }
}
