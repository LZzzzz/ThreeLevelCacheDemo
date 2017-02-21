package com.lzj.threelevelcachedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lzj.utils.BitmapUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png";
        ImageView image = (ImageView) findViewById(R.id.image);
        BitmapUtils bitmapUtils = new BitmapUtils();
        bitmapUtils.display(url, image);
    }
}
