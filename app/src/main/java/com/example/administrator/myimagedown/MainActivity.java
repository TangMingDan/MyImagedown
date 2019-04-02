package com.example.administrator.myimagedown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.myimagedown.Down.ImageLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.img);
        String url = "https://n.sinaimg.cn/front/520/w260h260/20190402/sx2b-hvcmeux5380071.jpg";
        ImageLoader loader = new ImageLoader();
        loader.displayImage(url,imageView);
    }
}
