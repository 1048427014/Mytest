package com.mtkj.mylvyou.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mtkj.mylvyou.R;
import com.mtkj.mylvyou.uilts.MTitlestatusUilts;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.loader.autohideime.HideIMEUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/13.
 */
@EActivity(R.layout.sousuo)
public class SousuoActivity extends BaseActivity {
    @ViewById(R.id.listmapd)
    ListView listmapd;
    List<Map<String,Object>> lists=new ArrayList<>();
    SimpleAdapter simpleAdapter;


    @Override
    public void Reisg() {
        MTitlestatusUilts.settitlestatus(SousuoActivity.this);
    }


    @Override
    public void initView() {
        HideIMEUtil.wrap(this);

        ArrayList<String> infoList = new ArrayList<String>();
        infoList = getIntent().getStringArrayListExtra("list");

        for (int i=0;i<infoList.size();i++){
            Map<String,Object> map=new HashMap<>();
            map.put("text",infoList.get(i));
            lists.add(map);
        }
        simpleAdapter=new SimpleAdapter(this,lists,R.layout.mapaddrelist_itme,new String[]{"text"},new int[]{R.id.itme_test});
        listmapd.setAdapter(simpleAdapter);


        listmapd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String list=lists.get(i).get("text").toString();
                EventBus.getDefault().post(list);
                finish();
            }
        });

    }
}
