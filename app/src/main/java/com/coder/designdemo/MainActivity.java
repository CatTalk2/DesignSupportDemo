package com.coder.designdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout drawerLayout;
    private RelativeLayout parentLayout;
    private FloatingActionButton fab,fabLocal,fabFavorite;
    private ImageView imageView;
    private NavigationView navigationView;

    //标识FloatingActionBar的打开状态
    private  int FAB_STATE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        parentLayout= (RelativeLayout) findViewById(R.id.parent_layout);
        imageView= (ImageView) findViewById(R.id.image);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        fabLocal= (FloatingActionButton) findViewById(R.id.fab_local);
        fabFavorite= (FloatingActionButton) findViewById(R.id.fab_favorite);
        navigationView= (NavigationView) findViewById(R.id.navigation);


        //navigationView选中Item处理,为了代码整齐些，就放在一个函数里了
        handNavigationView();

        fab.setOnClickListener(this);
        fabLocal.setOnClickListener(this);
        fabFavorite.setOnClickListener(this);

    }

    private void handNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            //用于辨别此前是否已有选中条目
            MenuItem preMenuItem;
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //首先将选中条目变为选中状态 即checked为true,后关闭Drawer，以前选中的Item需要变为未选中状态
                if(preMenuItem!=null)
                    preMenuItem.setChecked(false);
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                preMenuItem=menuItem;

                //不同item对应不同图片
                switch (menuItem.getItemId()){
                    case R.id.navigation_item1:
                        imageView.setImageResource(R.mipmap.bg_one);
                        break;
                    case R.id.navigation_item2:
                        imageView.setImageResource(R.mipmap.bg_two);
                        break;
                    case R.id.navigation_item3:
                        imageView.setImageResource(R.mipmap.bg_three);
                        break;
                    case R.id.navigation_sub_item1:
                        imageView.setImageResource(R.mipmap.bg_four);
                        break;
                    case R.id.navigation_sub_item2:
                        imageView.setImageResource(R.mipmap.bg_five);
                        break;
                    case R.id.navigation_sub_item3:
                        imageView.setImageResource(R.mipmap.bg_default);
                        break;
                }
                return true;
            }
        });
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){

//            点击fab弹出上面两个FAB，通过FAB_STATE判断当前fab的打开状态
            case R.id.fab:
                //如果没有打开，则弹出
                if(FAB_STATE==1){
                    fab.setImageResource(R.mipmap.ic_clear_white_24dp);
                    fabLocal.setVisibility(View.VISIBLE);
                    fabFavorite.setVisibility(View.VISIBLE);
                    FAB_STATE=0;
                    break;
                }
                //已经在打开状态，则关闭
                if(FAB_STATE==0){
                    fab.setImageResource(R.mipmap.ic_add_white_24dp);
                    fabLocal.setVisibility(View.GONE);
                    fabFavorite.setVisibility(View.GONE);
                    FAB_STATE=1;
                    break;
                }
                break;
            case R.id.fab_local:
                //弹出SnackBar，仅仅显示文字消息的SnackBar

                //注意第一个参数，需要一个合适的父视图
                Snackbar.make(parentLayout,"你点击了FAB_LOCAL",Snackbar.LENGTH_LONG)
                        .setAction("换个美女", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除按钮后的响应事件，比如换掉背景图片
                                imageView.setImageResource(R.mipmap.bg_six);
                            }
                        })
                        .show();
                break;
            case R.id.fab_favorite:
                //设置字体颜色
                Snackbar.make(parentLayout,"你点击了FAB_FAVORITE",Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //
                            }
                        }).setActionTextColor(R.color.action_text_color)
                        .show();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
