package com.lpf.refreshpage.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lpf.refreshpage.R;
import com.lpf.refreshpage.ui.fragment.RefreshGridViewFragment;
import com.lpf.refreshpage.ui.fragment.first;
import com.lpf.refreshpage.ui.fragment.second;
import com.lpf.refreshpage.widget.BGAViewPager;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.viewPager)
    BGAViewPager mViewPager;
    @InjectView(R.id.navigationView)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    protected void setListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                hideDrawer();

                setTitle(item.getTitle());
                switch (item.getItemId()) {
                    case R.id.navigation_main_gridview:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.navigation_main_normallistview:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.navigation_main_normalrecyclerview:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.navigation_main_swipelistview:
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.navigation_main_swiperecyclerview:
                        mViewPager.setCurrentItem(4, false);
                        break;
                    case R.id.navigation_main_staggeredgridlayoutmanager:
                        mViewPager.setCurrentItem(5, false);
                        break;
                    case R.id.navigation_main_scrollview:
                        mViewPager.setCurrentItem(6, false);
                        break;
                    case R.id.navigation_main_normalview:
                        mViewPager.setCurrentItem(7, false);
                        break;
                    case R.id.navigation_main_webview:
                        mViewPager.setCurrentItem(8, false);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        setTitle("GridViewDemo");

        setUpNavDrawer();
        setUpViewPager();
    }

    private void setUpNavDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void setUpViewPager() {
        mViewPager.setAllowUserScrollable(false);
        mViewPager.setAdapter(new ContentViewPagerAdapter(getSupportFragmentManager(),this));
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            hideDrawer();
        }else{
            super.onBackPressed();
        }
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private static class ContentViewPagerAdapter extends FragmentPagerAdapter {
        private Class[] mFragments = new Class[]{RefreshGridViewFragment.class, RefreshGridViewFragment.class,RefreshGridViewFragment.class, second.class,first.class, second.class};
        private Context mContext;

        public ContentViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(mContext, mFragments[position].getName());
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

}
