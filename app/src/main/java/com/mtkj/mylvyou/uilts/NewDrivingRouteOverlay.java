package com.mtkj.mylvyou.uilts;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.overlay.DrivingRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.mtkj.mylvyou.R;
//自定义线路样式
public class NewDrivingRouteOverlay extends DrivingRouteOverlay {
    Context mContext;
    public NewDrivingRouteOverlay(Context arg0, AMap arg1, DrivePath arg2,
                                  LatLonPoint arg3, LatLonPoint arg4) {
        super(arg0, arg1, arg2, arg3, arg4);
        mContext = arg0;
    }
    //自定义结束位置marker
    @Override
    protected BitmapDescriptor getEndBitmapDescriptor() {
        return BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(),  R.drawable.chufa));//此处添加你的图标
    }
    //自定义开始位置marker
    @Override
    protected BitmapDescriptor getStartBitmapDescriptor() {
        return BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.drawable.chufa));//此处添加你的图标
    }

    //自定义路线颜色
    @Override
    protected int getDriveColor() {

        return Color.CYAN;
    }


}
