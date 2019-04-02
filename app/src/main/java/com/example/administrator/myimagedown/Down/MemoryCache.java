package com.example.administrator.myimagedown.Down;

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryCache implements ImageCache{
    LruCache<String,Bitmap> mImageCache;
    public MemoryCache(){
        initImageCache();
    }

    private void initImageCache() {
        //获取系统分配给每个应用程序的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取内存的四分之一作为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url,bitmap);
    }
}
