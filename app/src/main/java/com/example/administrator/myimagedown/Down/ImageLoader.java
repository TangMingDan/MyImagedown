package com.example.administrator.myimagedown.Down;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    //图片缓存
    ImageCache mImageCache = new MemoryCache();

    //线程池，线程数量为CPU数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //UI Handle
    Handler mHandler = new Handler(Looper.getMainLooper());
    private void updateImageView(final ImageView imageView, final Bitmap bitmap){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
               imageView.setImageBitmap(bitmap);
            }
        });
    }
    // 设置缓存方式
    public void setmImageCache(ImageCache mImageCache) {
        this.mImageCache = mImageCache;
    }

    //显示图片
    public void displayImage(String url,ImageView imageView){
        Bitmap bitmap = mImageCache.get(url);
        if(bitmap != null){
            updateImageView(imageView,bitmap);
            return;
        }
        //如果没有缓存过，就提交申请下载
        submitDownload(url,imageView);
    }

    private void submitDownload(final String imageUrl, final ImageView imageView) {
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = download(imageUrl);
                if(bitmap == null){
                    return;
                }
                if(imageView.getTag().equals(imageUrl)){
                    updateImageView(imageView,bitmap);
                }
                mImageCache.put(imageUrl,bitmap);
            }
        });
    }

    //下载图片
    private Bitmap download(String imgurl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
