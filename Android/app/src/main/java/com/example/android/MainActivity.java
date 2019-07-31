package com.example.android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();

    //4개의 메뉴에 들어갈 Fragment들
    private Home home = new Home();
    private Notifications noti = new Notifications();
    private Alarm alarm = new Alarm();
    private Mypage mypage = new Mypage();

    private Button search_bar;
    private Button search_icon;
    private static final String TAG = "MainActivity";

    private String name,email,img_url;
    private TextView nav_name,nav_email;
    private ImageView nav_img;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent(); /*데이터 수신*/
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        img_url = intent.getStringExtra("img_url");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //View nav_header_view = navigationView.inflateHeaderView(R.layout.nav_header_main);
        View nav_header_view = navigationView.getHeaderView(0);

        nav_name = nav_header_view.findViewById(R.id.nav_name);
        nav_name.setText(name);
        nav_email = nav_header_view.findViewById(R.id.nav_email);
        nav_email.setText(email);
        nav_img = nav_header_view.findViewById(R.id.nav_img);

        //이미지 url에서 받아와서 변경
        Thread mThread = new Thread()
        {
            @Override
            public void run()
            {
             try{
                 URL url = new URL(img_url);
                 HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                 conn.setDoInput(true);
                 conn.connect();

                 InputStream is = conn.getInputStream();
                 bitmap = BitmapFactory.decodeStream(is);
             }
             catch (MalformedURLException e)
             {
                 e.printStackTrace();
             }
             catch (IOException e)
             {
                 e.printStackTrace();
             }
            }
        };

        mThread.start();

        try{
            mThread.join();
            nav_img.setImageBitmap(bitmap);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }



         BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

         // 첫 화면 지정
         FragmentTransaction transaction = fragmentManager.beginTransaction();
         transaction.replace(R.id.frame_layout,home).commitAllowingStateLoss();

         // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 FragmentTransaction transaction = fragmentManager.beginTransaction();
                 int id = item.getItemId();
                 switch (item.getItemId()) {
                     case R.id.navigation_home: {

                         transaction.replace(R.id.frame_layout, home).commitAllowingStateLoss();
                         break;
                     }
                     case R.id.navigation_notifications: {
                         transaction.replace(R.id.frame_layout, noti).commitAllowingStateLoss();
                         //Intent intent2 = new Intent(MainActivity.this,Notifications.class);

                         break;
                     }
                     case R.id.navigation_alarm: {

                         transaction.replace(R.id.frame_layout, alarm).commitAllowingStateLoss();
                         //Intent intent3 = new Intent(MainActivity.this,Alarm.class);

                         break;
                     }
                     case R.id.navigation_mypage: {
                         Log.d(TAG,"mypage click");
                         transaction.replace(R.id.frame_layout, mypage).commitAllowingStateLoss();
                         break;
                     }
                 }

                 return true;
             }
         });


     }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.navigation_home)
//        {
//            Log.d(TAG,"home");
//            Intent Intent = new Intent(this, Home.class);
//            startActivity(Intent);
//        }
//        if (id == R.id.navigation_alarm)
//        {
//            Log.d(TAG,"alarm");
//
//            Intent Intent = new Intent(this, Alarm.class);
//            startActivity(Intent);
//        }
//        if (id == R.id.navigation_notifications)
//        {
//            Log.d(TAG,"noti");
//            Intent Intent = new Intent(this, Notifications.class);
//            startActivity(Intent);
//        }
//        if (id == R.id.navigation_mypage)
//        {
//            Log.d(TAG,"mypage");
//
//            Intent Intent = new Intent(this, Mypage.class);
//            startActivity(Intent);
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Subscribers) {
            // Handle the camera action
        }
        else if (id == R.id.nav_Followers) {

        } else if (id == R.id.nav_Payment) {

        } else if (id == R.id.nav_Subscription) {

        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_ask) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
