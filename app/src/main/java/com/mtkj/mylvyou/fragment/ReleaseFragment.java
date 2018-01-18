package com.mtkj.mylvyou.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.mtkj.mylvyou.R;
import com.mtkj.mylvyou.activity.ChoosemapActivity;
import com.mtkj.mylvyou.uilts.TimecalculateUilts;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 *
 */
@EFragment(R.layout.releaseline)
public class ReleaseFragment extends BaseFragment implements View.OnClickListener {
    @ViewById(R.id.xuanzestatetime)
    RelativeLayout statetime;
    @ViewById
    TextView statutime;
    @ViewById
    RelativeLayout jieshutime;
    @ViewById
    TextView endtime;
    @ViewById
    TextView numtime;
    @ViewById
    LinearLayout chufaaddres;

    String kaishitime = null;
    String jiehustime = null;


    @Override
    public void FinitView() {
        statetime.setOnClickListener(this);
        chufaaddres.setOnClickListener(this);
        jieshutime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xuanzestatetime:
                DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(getActivity(), new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                        if (jiehustime == null) {
                            statutime.setText(dateDesc);
                        } else {
                            int a = TimecalculateUilts.Timecale(dateDesc, jiehustime);
                            if (a < 0) {
                                Toast.makeText(getActivity(), "时间选择错误", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                statutime.setText(dateDesc);
                                numtime.setText(a + "天");
                            }
                        }
                        kaishitime = dateDesc;

                    }
                }).textConfirm("CONFIRM") //text of confirm button
                        .textCancel("CANCEL") //text of cancel button
                        .btnTextSize(12) // button text size
                        .viewTextSize(18) // pick view text size
                        .colorCancel(Color.parseColor("#999999")) //color of cancel button
                        .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                        .minYear(1990) //min year in loop
                        .maxYear(2550) // max year in loop
                        .showDayMonthYear(false) // shows like dd mm yyyy (default is false)
                        .build();
                pickerPopWin.showPopWin(getActivity());
                break;
            case R.id.jieshutime:
                if (kaishitime != null) {
                    DatePickerPopWin pickerPopWin1 = new DatePickerPopWin.Builder(getActivity(), new DatePickerPopWin.OnDatePickedListener() {
                        @Override
                        public void onDatePickCompleted(int year, int month, int day, String dateDesc) {
                            int a = TimecalculateUilts.Timecale(kaishitime, dateDesc);
                            if (a <= 0) {
                                Toast.makeText(getActivity(), "时间选择错误", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            numtime.setText(a + "天");
                            Toast.makeText(getActivity(), "结果" + a, Toast.LENGTH_SHORT).show();
                            endtime.setText(dateDesc);
                            jiehustime = dateDesc;

                        }
                    }).textConfirm("CONFIRM") //text of confirm button
                            .textCancel("CANCEL") //text of cancel button
                            .btnTextSize(12) // button text size
                            .viewTextSize(18) // pick view text size
                            .colorCancel(Color.parseColor("#999999")) //color of cancel button
                            .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                            .minYear(1990) //min year in loop
                            .maxYear(2550) // max year in loop
                            .showDayMonthYear(false) // shows like dd mm yyyy (default is false)
                            .dateChose(kaishitime)
                            .build();
                    pickerPopWin1.showPopWin(getActivity());

                } else {
                    Toast.makeText(getActivity(), "请先选择出发时间", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.chufaaddres:
                startActivity(new Intent(getActivity(),ChoosemapActivity.class));
                break;
            default:
                break;
        }


    }
}
