package com.mtkj.mylvyou.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mtkj.mylvyou.R;
import com.mtkj.mylvyou.fragment.HomeFragment;
import com.mtkj.mylvyou.fragment.ReleaseFragment;
import com.mtkj.mylvyou.fragment.ReleaseFragment_;
import com.mtkj.mylvyou.uilts.MTitlestatusUilts;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 首页
 */
@EActivity(R.layout.index)
public class IndexActivity extends BaseActivity {

    @ViewById(R.id.bar)
    BottomNavigationBar bottomNavigationBar;
    public BadgeItem badgeItem;
    private HomeFragment homeFragment = null;
    private ReleaseFragment releaseFragment = null;

    @Override
    public void Reisg() {
        MTitlestatusUilts.settitlestatus(IndexActivity.this);
    }

    @Override
    public void initView() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.barbutton);//背景颜色
        bottomNavigationBar.setInActiveColor(R.color.weixuanzhong);//未选中时的颜色
        bottomNavigationBar.setActiveColor(R.color.xuanzhong);//选中时的颜色

        badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("new").setHideOnSelect(true);//角标
        //选项
        BottomNavigationItem msgItem = new BottomNavigationItem(R.drawable.shouye, "首页");
        BottomNavigationItem taskItem = new BottomNavigationItem(R.drawable.xianlu, "线路");
        BottomNavigationItem noticeItem = new BottomNavigationItem(R.drawable.faxian, "发现");
        noticeItem.setBadgeItem(badgeItem);
        BottomNavigationItem noticeItem1 = new BottomNavigationItem(R.drawable.huiyuan, "会员");
        BottomNavigationItem noticeItem2 = new BottomNavigationItem(R.drawable.wode, "我的");

        //添加选项初始化监听
        bottomNavigationBar.addItem(msgItem).addItem(taskItem).addItem(noticeItem).addItem(noticeItem1).addItem(noticeItem2);
        bottomNavigationBar.initialise();


        //初始化第一个fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        addfragment(ft);
        yfragment(ft);
        ft.show(homeFragment).commit();


        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                yfragment(ft);
                switch (position){
                    case 0:
                        ft.show(homeFragment).commit();
                        break;
                    case 1:
                        ft.show(releaseFragment).commit();
                        break;
                    case 2:

                        break;
                    default:

                        break;
                }
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });

    }





    //奇思妙想优雅切换fragment的方法A.A-------QAQ 蠢想法 还是用下面这种方法来切换吧 ε=(´ο｀*)))唉
//    public FragmentTransaction setTabfragment(Fragment fragment){
//        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//        return null;
//    }

    //先将fragmen预加载一下
    public void addfragment(FragmentTransaction ft) {
        if (releaseFragment == null) {
            releaseFragment = ReleaseFragment_.builder().build();
        }
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        ft.add(R.id.shiu, releaseFragment);
        ft.add(R.id.shiu, homeFragment);
    }

    //隐藏所有fragment
    public void yfragment(FragmentTransaction ft) {
        if (releaseFragment == null) {
            releaseFragment = ReleaseFragment_.builder().build();
        } else {
            ft.hide(releaseFragment);
        }
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        } else {
            ft.hide(homeFragment);
        }
    }


}
