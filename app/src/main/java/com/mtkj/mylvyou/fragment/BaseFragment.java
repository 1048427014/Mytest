package com.mtkj.mylvyou.fragment;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Administrator on 2018/1/8.
 */
@EFragment
public class BaseFragment extends Fragment {
    @AfterInject
    public void Frigs(){

    }

    @AfterViews
    protected void Fini(){
        FinitView();
        FinitData();
    }

    public void FinitData() {

    }

    public void FinitView() {
    }


}
