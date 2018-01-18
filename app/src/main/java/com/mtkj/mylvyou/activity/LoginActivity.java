package com.mtkj.mylvyou.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.dou361.dialogui.DialogUIUtils;
import com.mtkj.mylvyou.MyApplicat;
import com.mtkj.mylvyou.R;
import com.mtkj.mylvyou.net.Usernet;
import com.mtkj.mylvyou.net.evnt.UserLoginBaen;
import com.mtkj.mylvyou.net.hui.UserloginHui;
import com.mtkj.mylvyou.net.init.Userinit;
import com.mtkj.mylvyou.uilts.MTitlestatusUilts;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.loader.autohideime.HideIMEUtil;

/**
 * 登录
 */
@EActivity(R.layout.activity_main)
public class LoginActivity extends BaseActivity implements View.OnClickListener, UserloginHui {
//    @ViewById
//    ImageView image_qie;
    @ViewById
    EditText ed_username;
    @ViewById
    EditText ed_password;
    @ViewById
    Button bt_login;
    Dialog dialog;

    Userinit userinit = null;

    boolean statec_username=false;
    boolean statec_passwrod=false;

    @Override
    public void Reisg() {
        MTitlestatusUilts.settitlestatus(LoginActivity.this);
    }

    @Override
    public void initView() {
        HideIMEUtil.wrap(this);
//        image_qie.bringToFront();

//        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
//        alpha.setDuration(3000);
//        alpha.setFillAfter(true);
//        image_qie.setAnimation(alpha);
        bt_login.setOnClickListener(this);

        ed_password.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp=charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length()>=6&&temp.length()<=20){
                    statec_passwrod=true;
                    if (statec_username){
                        bt_login.setBackgroundColor(Color.parseColor("#e61531"));
                    }
                }else{
                    statec_passwrod=false;
                    bt_login.setBackgroundColor(Color.parseColor("#e2e2e2"));
                }

            }
        });
        ed_username.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp=charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (temp.length()==11){
                    statec_username=true;
                    if (statec_passwrod){
                        bt_login.setBackgroundColor(Color.parseColor("#e61531"));
                    }

                }else{
                    statec_username=false;
                    bt_login.setBackgroundColor(Color.parseColor("#e2e2e2"));
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String username = ed_username.getText().toString();
                String password = ed_password.getText().toString();

                if (!username.equals("") && !password.equals("")) {
                    if (username.length() == 11) {
                        if (password.length() >= 6 && password.length() <= 20) {
                            if (userinit == null) {
                                userinit = new Usernet();
                            }
                            dialog= DialogUIUtils.showLoading(this,"登录中~",true,false,false,true).show();
                            userinit.user_login(username, password, this);
                        } else {
                            Toast.makeText(this, "密码不得小于6位大于20位", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void LoginErres(String msg) {
        DialogUIUtils.dismiss(dialog);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginUserLoginBaen(UserLoginBaen userLoginBaen) {
        DialogUIUtils.dismiss(dialog);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        MyApplicat.USERNAME = userLoginBaen.getRes().get(0).getName();
        startActivity(new Intent(this, IndexActivity_.class));
    }
}
