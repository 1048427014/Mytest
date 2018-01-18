package com.mtkj.mylvyou.activity;

import com.mtkj.mylvyou.R;
import com.zhy.autolayout.AutoLayoutActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Administrator on 2018/1/8.
 */
@EActivity(R.layout.base)
public class BaseActivity extends AutoLayoutActivity {

    @AfterInject
    public void Reisg() {

    }

    @AfterViews
    protected void init() {
        initView();
        initData();
    }

    public void initView() {
    }

    public void initData() {
    }

}
