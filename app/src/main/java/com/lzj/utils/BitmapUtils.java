package com.lzj.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by LZJ on 2017/2/21.
 */

public class BitmapUtils {
    private static final String TAG = "BitmapUtils";
    private NetCacheUtils netCacheUtils;
    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    public BitmapUtils() {
        this.memoryCacheUtils = new MemoryCacheUtils();
        this.localCacheUtils = new LocalCacheUtils(memoryCacheUtils);
        this.netCacheUtils = new NetCacheUtils(localCacheUtils, memoryCacheUtils);
    }

    public void display(String url, ImageView imageView) {
        Bitmap bitmap = null;
        //1.先从内存取
        bitmap = memoryCacheUtils.getBitmap(url);
        if (bitmap != null) {
            Log.e(TAG, "从内存取");
            imageView.setImageBitmap(bitmap);
            return;
        }
        //2.内存没有从本地磁盘取
        bitmap = localCacheUtils.getLocalBitmap(url);
        if (bitmap != null) {
            Log.e(TAG, "从本地磁盘取");
            imageView.setImageBitmap(bitmap);
            return;
        }
        //3.都没缓存从网络取
        Log.e(TAG, "从网络取");
        netCacheUtils.getBitmapFromNet(imageView, url);
    }
}
