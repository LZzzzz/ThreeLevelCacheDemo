package com.lzj.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by LZJ on 2017/2/21.
 */

public class LocalCacheUtils {
    private final static String sdPath = Environment.getExternalStorageDirectory().getPath() + "/bitmap";
    private MemoryCacheUtils utils;

    public LocalCacheUtils(MemoryCacheUtils utils) {
        this.utils = utils;
    }


    public void setLocalBitmap(String url, Bitmap bitmap) {
        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(sdPath, fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            utils.setBitmap(url, bitmap);
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getLocalBitmap(String url) {
        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(sdPath, fileName);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
