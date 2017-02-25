package com.example.wjj.listviewseniortest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetUtils {
    public static InputStream getInputStream(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");

            int responscode = httpURLConnection.getResponseCode();
            if (responscode == httpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                return inputStream;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String inputStreamtoString(InputStream inputStream) {
        int len = 0;
        byte[] arr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (inputStream != null) {
            try {
                while ((len = inputStream.read(arr)) != -1) {
                    byteArrayOutputStream.write(arr, 0, len);
                }
                String data = byteArrayOutputStream.toString();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Map<String, Object>> getMaps(String json) {
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String imageUri = jsonObject.getString("titleimg");
                String title = jsonObject.getString("namecn");

                Map<String, Object> map = new HashMap<>();
                map.put("titleimg", imageUri);
                map.put("namecn", title);

                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }

}


