package com.example.administrator.myimagedown.Down;

import android.graphics.Bitmap;

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap);
}
