package edu.bjtu.gymclub.gymclub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;


import java.util.ArrayList;

import static android.support.v4.view.GravityCompat.START;

public class MainInterfaceActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<String> menuLists;
    private ArrayAdapter<String> adapter;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private RadioButton rb_sport, rb_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);
        initView();
    }


    private void initView(){

        manager = getSupportFragmentManager();

        rb_sport = (RadioButton) findViewById(R.id.rb_sport);
        rb_info = (RadioButton) findViewById(R.id.rb_info);


        //点击radioButton触发的事件
        rb_sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.content_layout,new sportFragment());

                transaction.commit();
            }
        });

        rb_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = manager.beginTransaction();
                transaction.replace(R.id.content_layout,new infoFragment());

                transaction.commit();
            }
        });
        rb_sport.setChecked(true);

        transaction = manager.beginTransaction();

        transaction.add(R.id.content_layout,new sportFragment());
        rb_sport.setChecked(true);
        transaction.commit();



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_drawer);
            int originalWidth = originalBitmap.getWidth();
            int originalHeight = originalBitmap.getHeight();
            int newWidth = 100;
            int newHeight = 150; // 自定义 高度 暂时没用
            float scale = ((float) newHeight) /originalHeight;
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap changedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,originalWidth, originalHeight, matrix, true);
            BitmapDrawable b=new BitmapDrawable(null, changedBitmap);
            actionBar.setHomeAsUpIndicator(b);
        }


        initSideView(mDrawerLayout);
    }


    private void initSideView(DrawerLayout drawerLayout){
//        mDrawerList = (ListView)findViewById(R.id.listview);//listview
//        menuLists = new ArrayList<String>();//list
//        menuLists.add("Home");
//        menuLists.add("Announcement");
//        menuLists.add("Schedule");
//        menuLists.add("Coaches");
//        adapter=new ArrayAdapter<String>(
//                this,android.R.layout.simple_list_item_1,menuLists
//        );
//        mDrawerList.setAdapter(adapter);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_announcement:
                        intent = new Intent(MainInterfaceActivity.this,AnnounceActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_schedule:
                        intent = new Intent(MainInterfaceActivity.this,ScheduleActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_coaches:
                        intent = new Intent(MainInterfaceActivity.this,CoachActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_share:
                        break;
                    case R.id.nav_send:
                        break;
                }


                return true;
            }

        }


        );
//        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(menuLists.get(position).equals("Home")){
//                    mDrawerLayout.closeDrawer(GravityCompat.START);
//                }
//                    if(menuLists.get(position).equals("Announcement")){
//                    Intent intent = new Intent(MainInterfaceActivity.this,AnnounceActivity.class);
//                    startActivity(intent);
//                }
//                if(menuLists.get(position).equals("Schedule")){
//                    Intent intent = new Intent(MainInterfaceActivity.this,ScheduleActivity.class);
//                    startActivity(intent);
//                }
//                if(menuLists.get(position).equals("Coaches")){
//                    Intent intent = new Intent(MainInterfaceActivity.this,CoachActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //打开侧滑栏
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }




}
