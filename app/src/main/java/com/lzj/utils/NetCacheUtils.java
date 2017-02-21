package com.lzj.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LZJ on 2017/2/21.
 */

public class NetCacheUtils {
    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.localCacheUtils = localCacheUtils;
        this.memoryCacheUtils = memoryCacheUtils;
    }

    public void getBitmapFromNet(ImageView imageView, String url) {
        MyTask task = new MyTask();
        task.execute(new Object[]{imageView, url});
    }

    class MyTask extends AsyncTask<Object, Void, Bitmap> {
        private String url;
        private ImageView imageView;

        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];
            Bitmap bitmap = downLoadBitmap(url);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }


    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    private Bitmap downLoadBitmap(String url) {
        try {
            URL download = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) download.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                memoryCacheUtils.setBitmap(url, bitmap);
                localCacheUtils.setLocalBitmap(url, bitmap);
                return bitmap;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
