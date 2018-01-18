package com.mtkj.mylvyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.mtkj.mylvyou.R;
import com.mtkj.mylvyou.activity.LoginActivity_;
import com.mtkj.mylvyou.uilts.NewDrivingRouteOverlay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class HomeFragment extends Fragment implements View.OnClickListener,RouteSearch.OnRouteSearchListener {

    MapView mapView = null;
    TextView gotologin;
    AMap aMap;
    private List<LatLonPoint> list_latLatLonPoints = new ArrayList<LatLonPoint>();
    private DriveRouteResult driveRouteResult;// 驾车模式查询结果
    private LatLonPoint lat_one = new LatLonPoint(30.0310554265,120.5749511719);
    private LatLonPoint lat_two = new LatLonPoint(29.8358789459,121.5856933594);
    private LatLonPoint startPoint = new LatLonPoint(30.2353405775, 120.1739501953);
    private LatLonPoint endPoint = new LatLonPoint(28.5845217194, 121.4538574219);

    private int drivingMode = RouteSearch.DrivingDefault;// 驾车默认模式
    RouteSearch routeSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homebase, container, false);

        mapView = (MapView) view.findViewById(R.id.map);
        gotologin = (TextView) view.findViewById(R.id.gotologin);

        gotologin.setOnClickListener(this);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);




        if (aMap == null) {
            aMap = mapView.getMap();
        }

        routeSearch = new RouteSearch(getActivity());
        routeSearch.setRouteSearchListener(this);



        list_latLatLonPoints.add(lat_one);
        list_latLatLonPoints.add(lat_two);


        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, drivingMode, list_latLatLonPoints, null, "");
        routeSearch.calculateDriveRouteAsyn(query);

        return view;
    }

    /**
     * 必须重写以下方法
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gotologin:
                Intent intent = new Intent(getActivity(), LoginActivity_.class);
                startActivity(intent);
                break;
            default:
                break;


        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
        Log.e("aa", "onBusRouteSearched" + i);
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
        Toast.makeText(getActivity(), ""+rCode, Toast.LENGTH_SHORT).show();
            if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
                driveRouteResult = result;
                DrivePath drivePath = driveRouteResult.getPaths().get(0);
                aMap.clear();// 清理地图上的所有覆盖物
                NewDrivingRouteOverlay drivingRouteOverlay = new NewDrivingRouteOverlay(getActivity(), aMap, drivePath, driveRouteResult.getStartPos(),
                        driveRouteResult.getTargetPos());
                drivingRouteOverlay.setNodeIconVisibility(false);
                drivingRouteOverlay.removeFromMap();
                drivingRouteOverlay.addToMap();
                drivingRouteOverlay.zoomToSpan();

        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
//        RouteTool walkRouteOverlay = new RouteTool(getActivity(), aMap, walkRouteResult.getPaths().get(0), walkRouteResult.getStartPos(),walkRouteResult.getTargetPos());
//        walkRouteOverlay.setView(R.color.liji_material_red_500,200);

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
        Log.e("aa", "onRideRouteSearched" + i);
    }

}