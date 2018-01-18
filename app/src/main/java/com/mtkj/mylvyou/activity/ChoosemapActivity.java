package com.mtkj.mylvyou.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.dou361.dialogui.DialogUIUtils;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.mtkj.mylvyou.R;
import com.mtkj.mylvyou.adpter.AddreschoiceAdapter;
import com.mtkj.mylvyou.uilts.MTitlestatusUilts;
import com.mtkj.mylvyou.uilts.NewDrivingRouteOverlay;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.loader.autohideime.HideIMEUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选择线路位置
 */

public class ChoosemapActivity extends AutoLayoutActivity implements AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, GeocodeSearch.OnGeocodeSearchListener,View.OnClickListener,RouteSearch.OnRouteSearchListener {
    MapView mMapView = null;
    AMap amap = null;
    GeocodeSearch geocodeSearch = null;
    EditText et_searchtext_search;
    TextView buttonback;
    ArrayList<String> lists = new ArrayList<>();
    TextView weizhi;
    ImageView xiala;
    RecyclerView recyelerview;
    LatLng target;
    Button xuanzeweizhi;
    List<Map<String,Object>> listmap=new ArrayList<>();
    List<Map<String,Object>> listlatlng=new ArrayList<>();
    List<Map<String,Object>> linealistmap=new ArrayList<>();
    private List<LatLonPoint> list_latLatLonPoints = new ArrayList<LatLonPoint>();
    AddreschoiceAdapter addreschoiceAdapter;
    TextView text_name;
    TextView endname;
    Button sheweiqidian;
    Button setupend;
    Button setpathpoint;
    Button delectpathpoint;
    Button delectnotall;
    String address="浙江省台州市";
    Dialog dialog;
    int rt=0;



    private int drivingMode = RouteSearch.DrivingDefault;// 驾车默认模式
    private DriveRouteResult driveRouteResult;// 驾车模式查询结果
    RouteSearch routeSearch;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setmaplist(String name){
        Map<String,Object> map=new HashMap<>();
        map.put("text",address+name);
        listmap.add(map);
        addreschoiceAdapter.notifyDataSetChanged();
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MTitlestatusUilts.settitlestatus(ChoosemapActivity.this);
        setContentView(R.layout.choosemap);
        HideIMEUtil.wrap(this);
        EventBus.getDefault().register(this);

        delectnotall= (Button) findViewById(R.id.delectnotall);
        delectpathpoint= (Button) findViewById(R.id.delectpathpoint);
        setpathpoint= (Button) findViewById(R.id.setpathpoint);
        endname= (TextView) findViewById(R.id.end_name);
        text_name= (TextView) findViewById(R.id.text_name);
        sheweiqidian= (Button) findViewById(R.id.sheweiqidian);
        xuanzeweizhi= (Button) findViewById(R.id.xuanzeweizhi);
        recyelerview= (RecyclerView) findViewById(R.id.recyelerview);
        mMapView = (MapView) findViewById(R.id.map);
        buttonback = (TextView) findViewById(R.id.buttonback);
        weizhi= (TextView) findViewById(R.id.weizhi);
        xiala= (ImageView) findViewById(R.id.xiala);
        et_searchtext_search = (EditText) findViewById(R.id.et_searchtext_search);
        setupend= (Button) findViewById(R.id.setupend);

        //创建地图
        mMapView.onCreate(savedInstanceState);
        //创建地址转坐标对象
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        if (amap == null) {
            amap = mMapView.getMap();
        }

        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);


        listmap.clear();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyelerview.setLayoutManager(layoutManager);
        addreschoiceAdapter=new AddreschoiceAdapter(listmap,this);
        recyelerview.setAdapter(addreschoiceAdapter);

        delectnotall.setOnClickListener(this);
        delectpathpoint.setOnClickListener(this);
        setpathpoint.setOnClickListener(this);
        setupend.setOnClickListener(this);
        xuanzeweizhi.setOnClickListener(this);
        amap.setOnCameraChangeListener(this);
        buttonback.setOnClickListener(this);
        weizhi.setOnClickListener(this);
        xiala.setOnClickListener(this);
        sheweiqidian.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    //获取中心点坐标
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        target = cameraPosition.target;

    }


    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    //poi返回结果
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {
            if (poiResult.getPois().size() >= 0) {
                lists.clear();
                for (int z = 0; z < poiResult.getPois().size(); z++) {
                    lists.add(poiResult.getPois().get(z).toString());
                }
                Intent intent = new Intent(this, SousuoActivity_.class);
                intent.putStringArrayListExtra("list", lists);
                startActivity(intent);
            }

//            GeocodeQuery query = new GeocodeQuery("天时大厦(东北门)", "台州市");
//            geocodeSearch.getFromLocationNameAsyn(query);
        } else {
            Log.e("aa", "失败返回代码=" + i);
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    //逆地理编码
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

        if (rt==1){
            //设置起点
            text_name.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress().toString());
            rt=0;
            if (!endname.getText().toString().equals("终点")&&!text_name.getText().toString().equals("起点")){
                dialog= DialogUIUtils.showLoading(this,"线路规划中",true,false,false,true).show();
                GeocodeQuery queryend = new GeocodeQuery(endname.getText().toString(),endname.getText().toString());
                geocodeSearch.getFromLocationNameAsyn(queryend);

                GeocodeQuery querystart = new GeocodeQuery(text_name.getText().toString(),text_name.getText().toString());
                geocodeSearch.getFromLocationNameAsyn(querystart);
            }
        }else if (rt==2){
            endname.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress().toString());
            rt=0;
            if (!endname.getText().toString().equals("终点")&&!text_name.getText().toString().equals("起点")){
                dialog= DialogUIUtils.showLoading(this,"线路规划中",true,false,false,true).show();
                GeocodeQuery queryend = new GeocodeQuery(endname.getText().toString(),endname.getText().toString());
                geocodeSearch.getFromLocationNameAsyn(queryend);

                GeocodeQuery querystart = new GeocodeQuery(text_name.getText().toString(),text_name.getText().toString());
                geocodeSearch.getFromLocationNameAsyn(querystart);
            }
        }
        else {
            Map<String, Object> map = new HashMap<>();
            map.put("text", regeocodeResult.getRegeocodeAddress().getFormatAddress());
            listmap.add(map);
            addreschoiceAdapter.notifyDataSetChanged();
        }
    }

    //地理编码
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == 1000) {
            GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
            //获取到的经纬度
            LatLonPoint latLongPoint = address.getLatLonPoint();
            float Lat = (float) latLongPoint.getLatitude();
            float Lon = (float) latLongPoint.getLongitude();
            Map<String,Object> map=new HashMap<>();
            map.put("lat",Lat);
            map.put("lng",Lon);
            linealistmap.add(map);
            if (linealistmap.size()>1){
                    LatLonPoint startPoint = new LatLonPoint((float)linealistmap.get(0).get("lat"),(float) linealistmap.get(0).get("lng"));
                    LatLonPoint endPoint = new LatLonPoint((float)linealistmap.get(1).get("lat"),(float) linealistmap.get(1).get("lng"));
                    RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(startPoint, endPoint);
                //加入点生成路线规划
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, drivingMode, list_latLatLonPoints, null, "");
                routeSearch.calculateDriveRouteAsyn(query);
            }

        } else {
            Log.e("aa", "位置转坐标失败");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {

                    return;
                }
                Bundle bundle = data.getExtras();

                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");

                if (null == cityInfoBean) {
                    return;
                }
                address=cityInfoBean.getName();
                weizhi.setText(cityInfoBean.getName());
            }
        }
    }
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }
    //路线规划回调
    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
        if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
            driveRouteResult = result;
            DrivePath drivePath = driveRouteResult.getPaths().get(0);
            amap.clear();// 清理地图上的所有覆盖物
            NewDrivingRouteOverlay  drivingRouteOverlay = new NewDrivingRouteOverlay(this, amap, drivePath, driveRouteResult.getStartPos(),
                        driveRouteResult.getTargetPos());
            drivingRouteOverlay.setNodeIconVisibility(false);
            drivingRouteOverlay.addToMap();
            drivingRouteOverlay.zoomToSpan();

//            for (int i=0;i<listlatlng.size()-1;i++) {
//                LatLng latLng = new LatLng((float)listlatlng.get(i).get("lat"),(float)listlatlng.get(i).get("lng"));
//                final Marker marker = amap.addMarker(new MarkerOptions().position(latLng).title("途径点").snippet(linealistmap.get(i).get("text").toString()));
//            }

            DialogUIUtils.dismiss(dialog);
        }else{
            DialogUIUtils.dismiss(dialog);
            Toast.makeText(this, "不好意思规划出错了~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonback:
                PoiSearch.Query query = new PoiSearch.Query(et_searchtext_search.getText().toString(), "", weizhi.getText().toString());
                query.setPageSize(100);//每页最多返回多少
                query.setPageNum(1);//设置查询的页码
                //构造poi对象开始查询
                PoiSearch poiSearch = new PoiSearch(ChoosemapActivity.this, query);
                //设置监听对象
                poiSearch.setOnPoiSearchListener(ChoosemapActivity.this);
                poiSearch.searchPOIAsyn();
                break;
            case R.id.xiala:
                Intent intent = new Intent(ChoosemapActivity.this, CityListSelectActivity.class);
                startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
                break;
            case R.id.weizhi:
                Intent intent1 = new Intent(ChoosemapActivity.this, CityListSelectActivity.class);
                startActivityForResult(intent1, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
                break;
            case R.id.xuanzeweizhi:
                //添加线路成功

                for (int i=0;i<=(listmap.size()-1);i++){

                    Log.e("aa","lat="+listlatlng.get(i).get("lat").toString()+"lng="+listlatlng.get(i).get("lng")+"地址"+listmap.get(i).get("text").toString());
                }

                break;
            case R.id.sheweiqidian:
                //设为起点
                linealistmap.clear();//清除之前保存的坐标
                if (target!=null) {
                    rt = 1;
                    LatLonPoint qd = new LatLonPoint(target.latitude, target.longitude);
                    RegeocodeQuery queryqd = new RegeocodeQuery(qd, 200, GeocodeSearch.AMAP);
                    geocodeSearch.getFromLocationAsyn(queryqd);
                }
                break;
            case R.id.setupend:
                //设为终点
                linealistmap.clear();
                if (target!=null) {
                    rt = 2;
                    LatLonPoint zd = new LatLonPoint(target.latitude, target.longitude);
                    RegeocodeQuery queryzd = new RegeocodeQuery(zd, 200, GeocodeSearch.AMAP);
                    geocodeSearch.getFromLocationAsyn(queryzd);
                }
                break;
            case R.id.setpathpoint:
                //设置途径点
                if (target!=null) {
                    Map<String,Object> map=new HashMap<>();
                    map.put("lat",target.latitude);
                    map.put("lng",target.longitude);
                    LatLonPoint endPoint = new LatLonPoint(target.latitude,target.longitude);
                    list_latLatLonPoints.add(endPoint);
                    //判断是否选好终点与起点
                    if (!endname.getText().toString().equals("终点")&&!text_name.getText().toString().equals("起点")) {
                        if (linealistmap.size()>1) {
                            dialog= DialogUIUtils.showLoading(this,"线路规划中",true,false,false,true).show();

                            LatLonPoint startPointc = new LatLonPoint((float) linealistmap.get(0).get("lat"), (float) linealistmap.get(0).get("lng"));
                            LatLonPoint endPointc = new LatLonPoint((float) linealistmap.get(1).get("lat"), (float) linealistmap.get(1).get("lng"));
                            RouteSearch.FromAndTo fromAndToc = new RouteSearch.FromAndTo(startPointc, endPointc);
                            //加入点生成路线规划
                            RouteSearch.DriveRouteQuery queryc = new RouteSearch.DriveRouteQuery(fromAndToc, drivingMode, list_latLatLonPoints, null, "");
                            routeSearch.calculateDriveRouteAsyn(queryc);
                        }
                    }


                    listlatlng.add(map);
                    LatLonPoint lp = new LatLonPoint(target.latitude, target.longitude);
                    RegeocodeQuery querylatlng = new RegeocodeQuery(lp, 200, GeocodeSearch.AMAP);
                    geocodeSearch.getFromLocationAsyn(querylatlng);
                }else {
                    Toast.makeText(this, "移动地图中心点", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.delectpathpoint:
                //删除途经点
                if (listmap.size()!=0){
                    listlatlng.remove(listlatlng.size()-1);
                    listmap.remove(listmap.size()-1);
                    list_latLatLonPoints.remove(list_latLatLonPoints.size()-1);
                    addreschoiceAdapter.notifyDataSetChanged();

                    //判断是否选好终点与起点
                    if (!endname.getText().toString().equals("终点")&&!text_name.getText().toString().equals("起点")) {
                        if (linealistmap.size()>1) {
                            dialog= DialogUIUtils.showLoading(this,"线路规划中",true,false,false,true).show();
                            LatLonPoint startPointc = new LatLonPoint((float) linealistmap.get(0).get("lat"), (float) linealistmap.get(0).get("lng"));
                            LatLonPoint endPointc = new LatLonPoint((float) linealistmap.get(1).get("lat"), (float) linealistmap.get(1).get("lng"));
                            RouteSearch.FromAndTo fromAndToc = new RouteSearch.FromAndTo(startPointc, endPointc);
                            //加入点生成路线规划
                            RouteSearch.DriveRouteQuery queryc = new RouteSearch.DriveRouteQuery(fromAndToc, drivingMode, list_latLatLonPoints, null, "");
                            routeSearch.calculateDriveRouteAsyn(queryc);
                        }
                    }
                }

                break;
            case R.id.delectnotall:
                //全部删除
                text_name.setText("起点");
                endname.setText("终点");
                listlatlng.clear();
                listmap.clear();
                list_latLatLonPoints.clear();
                addreschoiceAdapter.notifyDataSetChanged();
                break;

        }
    }
}
