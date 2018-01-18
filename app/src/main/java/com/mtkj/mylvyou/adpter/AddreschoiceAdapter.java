package com.mtkj.mylvyou.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtkj.mylvyou.R;

import java.util.List;
import java.util.Map;

/**
 * Created by a123 on 18/1/16.
 */

public class AddreschoiceAdapter extends RecyclerView.Adapter<AddreschoiceAdapter.MlistViewHolder> {


    private List<Map<String, Object>> listmaps;
    private Context context;
    LayoutInflater layoutInflater;
    public AddreschoiceAdapter(List<Map<String,Object>> listmaps, Context context){
        this.listmaps = listmaps;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);

    }

    @Override
    public MlistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = layoutInflater.inflate(R.layout.addresschoice_itme, null, false);
        return new MlistViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MlistViewHolder mlistViewHolder, int i) {


        mlistViewHolder.name.setText(listmaps.get(i).get("text").toString());

    }

    @Override
    public int getItemCount() {
        return listmaps.size();
    }

    class MlistViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public MlistViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.text_name);
        }
    }
}
