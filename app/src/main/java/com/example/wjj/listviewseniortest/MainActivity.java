package com.example.wjj.listviewseniortest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.Manifest;


public class MainActivity extends AppCompatActivity {

    private String path = "http://openapi.db.39.net/app/GetDrugCompany?sign=9DFAAD5404FCB6168EA6840DCDFF39E5&app_key=app";
    private ListView listView_medicine;

    private SimpleAdapter adapter;
    private List<Map<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_medicine = (ListView) this.findViewById(R.id.listView_Medicine);

        new MyAsyncTask().execute(path);
    }

    private final class MyAsyncTask extends AsyncTask<String, Void, List<Map<String, Object>>> {

        @Override
        protected List<Map<String, Object>> doInBackground(String... strings) {

            InputStream inputStream = NetUtils.getInputStream(strings[0]);
            if (inputStream != null) {
                String json = NetUtils.inputStreamtoString(inputStream);
                if (json != null) {
                    return NetUtils.getMaps(json);
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> maps) {
            if (maps != null && maps.size() != 0) {
                data.addAll(maps);
                if (adapter == null) {
                    adapter = new SimpleAdapter(MainActivity.this, data, R.layout.single_unit_layout, new String[]{"titleimg", "namecn"}, new int[]{R.id.imageView_unit, R.id.textView_unit});
                    listView_medicine.setAdapter(adapter);

                    adapter.setViewBinder(new SimpleAdapter.ViewBinder() {

                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {
                            if (view instanceof ImageView) {
                                final ImageView imageView_img = (ImageView) view;
                                String url = textRepresentation;

                                class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
                                    @Override
                                    protected Bitmap doInBackground(String... params) {
                                        InputStream inputStream = NetUtils.getInputStream(params[0]);
                                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                                        return bitmap;
                                    }

                                    @Override
                                    protected void onPostExecute(Bitmap bitmap) {
                                        if (bitmap != null) {
                                            imageView_img.setImageBitmap(bitmap);
                                        }
                                    }
                                }

                                new LoadImageAsyncTask().execute(url);

                                return true;
                            }
                            return false;
                        }
                    });
                }
            }
        }

    }

}

