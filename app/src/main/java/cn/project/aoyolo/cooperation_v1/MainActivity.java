package cn.project.aoyolo.cooperation_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import cn.project.aoyolo.cooperation_v1.activity.MyInfoActivity;
import cn.project.aoyolo.cooperation_v1.ui.main.FuwuFragment;
import cn.project.aoyolo.cooperation_v1.ui.main.MyFragment;
import cn.project.aoyolo.cooperation_v1.ui.main.XuqiuFragment;
import cn.project.aoyolo.cooperation_v1.widget.CommonFragmentPagerAdapter;
import cn.project.aoyolo.cooperation_v1.widget.RoundCornerImageView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // 用RadioGroup来实现底部导航菜单。
    private RadioGroup group;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    // 用ViewPager来实现页面的切换。
    private ViewPager pager;
    private final int rbtID[] = new int[]{R.id.rbt_my, R.id.rbt_fuwu, R.id.rbt_xuqiu};
    private RadioButton rbGroup[];
    private List<Fragment> mPagesFragments;
    private RoundCornerImageView myInfoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        pager = (ViewPager) findViewById(R.id.viewpager_main);
        group = (RadioGroup) findViewById(R.id.radiogroup_main);
        rbGroup = new RadioButton[rbtID.length];
        for (int i = 0; i < rbtID.length; ++i) {
            rbGroup[i] = (RadioButton) findViewById(rbtID[i]);
        }
        // 初始化页面.
        mPagesFragments = new ArrayList<Fragment>();
        //我的界面
        mPagesFragments.add(new MyFragment());
        //服务界面
        mPagesFragments.add(new FuwuFragment());
        //需求界面
        mPagesFragments.add(new XuqiuFragment());
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(
                getSupportFragmentManager(), mPagesFragments);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);
        pager.setCurrentItem(1, false);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_my:
                        pager.setCurrentItem(0, false);
                        break;
                    case R.id.rbt_fuwu:
                        pager.setCurrentItem(1, false);
                        break;
                    case R.id.rbt_xuqiu:
                        pager.setCurrentItem(2, false);
                        break;
                }
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rbGroup[position].setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initView();
    }

    /**
     * 初始化界面信息
     * 这样才可以添加头部点击事件
     */
    private void initView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);//侧边栏
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);//画布
        addHeadView();//添加头布局
        setupnavigationView();
    }

    /**
     * 添加侧边栏的头部
     *
     */
    private void addHeadView() {
        //动态添加头布局，因为静态头布局无法引入里面的控件
        View view = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
        ,ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.nav_header);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                400));
        RoundCornerImageView headerView = (RoundCornerImageView) linearLayout.findViewById(R.id.imageView);
        navigationView.addHeaderView(view);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MyInfoActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
    }

    /**
     * 侧边栏选项点击事件
     */
    private void setupnavigationView() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        showToast("");
                        drawerLayout.closeDrawers();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.imageView) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
