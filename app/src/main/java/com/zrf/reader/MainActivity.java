package com.zrf.reader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-02-23 19:24
 * 主界面  DrawLayout和NavigationView AppBar：ToolBar和TabLayout   TabLayout和ViewPager结合使用
 * ViewPager:Fragment(SwipeRefreshLayout,RecyclerView(item:CardView封装))  FloatingActionButton
 */
public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
//    @Bind(R.id.tabs)
//    TabLayout mTabs;
//    @Bind(R.id.viewPager)
//    ViewPager mViewPager;

    private LatestFragment mLatestFragment;
    private ThemeFragment mThemeFragment;
    private long lastTime; //用于存上次按返回键的时间 在一定时间内按两次返回键  退出应用
//    private String[] mTitles;   //TabLayout 的tab 标题
//    private List<Fragment> mFragments;  //ViewPager的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //测试一下 material dialog
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.setDrawerListener(toggle);
        mNavView.setNavigationItemSelectedListener(this);
        mNavView.setCheckedItem(R.id.nav_home);
//        mTitles=getResources().getStringArray(R.array.tab_titles);
//        mFragments=new ArrayList<>();
//        for (int i = 0; i <mTitles.length ; i++) {
//            //如果TabLayout和ViewPager一起使用 起作用的setTabsFromPagerAdapter()  addTag无效
//            //两种为TabLayout 添加Tab的方法：1.addTab() 2.setTabsFromPagerAdapter()
////            mTabs.addTab(mTabs.newTab().setText(mTitles[i]));
//            mFragments.add(new LatestFragment());
//        }
//        MyFragAdapter myFragAdapter =new MyFragAdapter(
//                getSupportFragmentManager(),mFragments,mTitles);
//        mViewPager.setAdapter(myFragAdapter);
//        //TabLayout 和 ViewPager关联
//        mTabs.setupWithViewPager(mViewPager);
//        //通过PagerAdapter来设置Tabs 如果TabLayout和ViewPager结合使用 只能通过这样设置Tabs addTag无效
//        //在MyFragAdapter :: FragmentStatePagerAdapter 里设置title
//        mTabs.setTabsFromPagerAdapter(myFragAdapter);
        mLatestFragment =new LatestFragment();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fl_content, mLatestFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_search:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

//        mDrawerLayout.closeDrawer(GravityCompat.START);
        item.setChecked(true);
        selectNavItem(item.getItemId());
        mDrawerLayout.closeDrawers();
        return true;
    }

    private void selectNavItem(int id) {
        switch (id) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fl_content, mLatestFragment).commit();
                break;
            default:
                mThemeFragment=new ThemeFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("themeId",getThemeId(id));
                mThemeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl_content,mThemeFragment).commit();

                break;
        }
    }

    // 返回键的监听/回调 按两次返回键 退出应用
    @Override
    public void onBackPressed() {
        //如果DrawLayout是处于打开Drawer（即是NavigationView 打开）的状态 返回键 则是关闭Drawer
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime > 2000) {
                Snackbar.make(mFab, "再按一次退出", Snackbar.LENGTH_SHORT).
                        setAction("退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
                lastTime = currentTime;
            } else {
                finish();
            }
        }
    }

    public int getThemeId(int id) {
        int themeId=-1;
        switch (id){
            case R.id.nav_psychology:
                themeId=13;
                break;
            case R.id.nav_user_recommended:
                themeId=12;
                break;
            case R.id.nav_movie:
                themeId=3;
                break;
            case R.id.nav_not_allowed_bored:
                themeId=11;
                break;
            case R.id.nav_design:
                themeId=4;
                break;
            case R.id.nav_big_company:
                themeId=5;
                break;
            case R.id.nav_economic:
                themeId=6;
                break;
            case R.id.nav_internet_safety:
                themeId=10;
                break;
            case R.id.nav_start_games:
                themeId=2;
                break;
            case R.id.nav_music:
                themeId=7;
                break;
            case R.id.nav_cartoon:
                themeId=9;
                break;
            case R.id.nav_sports:
                themeId=8;
                break;
        }
        return themeId;
    }
}
