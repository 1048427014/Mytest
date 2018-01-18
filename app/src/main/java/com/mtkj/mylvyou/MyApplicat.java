package com.mtkj.mylvyou;

import android.app.Application;

import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citypickerview.CityPickerView;

/**
 * Created by Administrator on 2018/1/8.
 */

public class MyApplicat extends Application {

    public static String USERNAME;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        CityPickerView.getInstance().init(this);
        /**
         * 预先加载一级列表所有城市的数据
         */
        CityListLoader.getInstance().loadCityData(this);
    }


}
