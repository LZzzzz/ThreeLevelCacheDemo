package com.lzj.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by LZJ on 2017/2/21.
 */

public class MemoryCacheUtils {
    private LruCache<String, Bitmap> cache;

    public MemoryCacheUtils() {
        //缓存为应用最大内存的1/8
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        cache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getHeight() * value.getWidth();
            }
        };
    }

    public void setBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }

    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }
}
