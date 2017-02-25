package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.utils.Car;
import com.example.myapplication.utils.NetUtils;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private String path = "http://openapi.db.39.net/app/GetDrugCompany?sign=9DFAAD5404FCB6168EA6840DCDFF39E5&app_key=app";
    List<Car.ResultsEntity> results;
    private static final String TAG = "MainActivity";
    private ListView listView;
    private Handler handler;
    private MyAdapter adapter;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

    }

    private void initView() {
        if (isFirst) {
            listView = (ListView) this.findViewById(R.id.listView);
            adapter = new MyAdapter();
            listView.setAdapter(adapter);
        } else {
            //刷新数据
            adapter.notifyDataSetChanged();
        }
    }

    private void initData() {
        //实例化处理器：控制加载数据源initData( )和初始化视图initView( )的先后顺序.
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //初始化视图
                        initView();
                        break;
                }
            }
        };
        //开启子线程 初始化数据源results
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = NetUtils.getInputStream(path);
                if (inputStream != null) {
                    String json = NetUtils.getJsonString(inputStream);
                    if (json != null) {
                        Car car = NetUtils.parsetoT(json, Car.class);
                        results = car.getResults();
                        Log.d(TAG, "run: " + results.toString());
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        }).start();


    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return results.size();
        }

        @Override
        public Object getItem(int i) {
            return results.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;

            //判断convertView是否为空，避免重新加载布局
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.single_layout, null);
                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Car.ResultsEntity car = results.get(i);
            viewHolder.textView.setText(car.getNamecn());
            Picasso.with(MainActivity.this).load(car.getTitleimg()).placeholder(R.mipmap.ic_launcher).into(viewHolder.imageView);

            return convertView;
        }

        //自定义ViewHolder缓存控件实例
        class ViewHolder {
            ImageView imageView;
            TextView textView;
        }
    }
}
