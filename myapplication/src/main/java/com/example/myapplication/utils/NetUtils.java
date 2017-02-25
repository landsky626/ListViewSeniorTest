package com.example.myapplication.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtils {

    //从网址获取输入流
    public static InputStream getInputStream(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(3000);

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将输入流转化为json字符串
    public static String getJsonString(InputStream inputStream) {
        int len = 0;
        byte[] arr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(arr)) != -1) {
                    byteArrayOutputStream.write(arr, 0, len);
                }
                String data = byteArrayOutputStream.toString();
                Log.d("NetUtils", "getJsonString: " + data);
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    //解析json字符串的通用类
    public static <T> T parsetoT(String json, Class<T> cls) {

        Gson gson = new Gson();
        T t = gson.fromJson(json, cls);
        return t;

    }


}
