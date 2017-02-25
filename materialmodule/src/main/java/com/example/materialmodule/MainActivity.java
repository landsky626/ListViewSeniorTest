package com.example.materialmodule;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_countDown;
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置toolBar
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        initView();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_manage);

        navigationView.setCheckedItem(R.id.more_nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //菜单填充器填充菜单
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                break;
            case R.id.delete:
                break;
            case R.id.more:
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private void initView() {
        drawerLayout = (DrawerLayout) this.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) this.findViewById(R.id.navigationView);
        button_countDown = (Button) this.findViewById(R.id.button_countDown);
        button_countDown.setOnClickListener(this);
        floatingActionButton = (FloatingActionButton) this.findViewById(R.id.floatingActionBtn);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_countDown:
                new countDownTimer<>(button_countDown);
                break;
            case R.id.floatingActionBtn:
                Snackbar.make(view,"tip:snackbar show...",Snackbar.LENGTH_SHORT).setAction("next", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "next sight", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }

    //倒计时的类：CountDownTimer
    public class countDownTimer<T> extends CountDownTimer {

        private Button button;

        public countDownTimer(T text) {
            //时长，刷新间隔
            super(30000, 1000);
            Log.d(TAG, "countDownTimer: " + Thread.currentThread().getName());
            this.button = (Button) text;
            button.setClickable(false);
            start();
        }

        @Override
        public void onTick(long l) {
            Log.d(TAG, "onTick: " + Thread.currentThread().getName());
            button.setText(String.format("%s秒", l / 1000));
        }

        @Override
        public void onFinish() {
            Log.d(TAG, "onFinish: " + Thread.currentThread().getName());
            button.setText("获取");
            button.setClickable(true);
        }
    }
}
