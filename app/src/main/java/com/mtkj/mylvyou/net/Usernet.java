package com.mtkj.mylvyou.net;

import android.util.Log;

import com.mtkj.mylvyou.net.evnt.UserLoginBaen;
import com.mtkj.mylvyou.net.http.Userhttp;
import com.mtkj.mylvyou.net.hui.UserloginHui;
import com.mtkj.mylvyou.net.init.Userinit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/8.
 */

public class Usernet implements Userinit {

    private Userhttp userhttp;

    public Usernet() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.13:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userhttp=retrofit.create(Userhttp.class);
    }

    @Override
    public void user_login(String username, String password, final UserloginHui userloginHui) {
        Call<UserLoginBaen> userlogin = userhttp.userlogin(username, password);
        userlogin.enqueue(new Callback<UserLoginBaen>() {
            @Override
            public void onResponse(Call<UserLoginBaen> call, Response<UserLoginBaen> response) {
                if (response.body()!=null){
                    if (response.body().getRt()==1) {
                        userloginHui.LoginUserLoginBaen(response.body());
                    }else {
                        userloginHui.LoginErres(response.body().getMsg());
                    }
                }else{
                    userloginHui.LoginErres("数据错误");
                }
            }

            @Override
            public void onFailure(Call<UserLoginBaen> call, Throwable t) {
                userloginHui.LoginErres("网络连接错误");
                Log.e("aa",call.toString());
            }
        });


    }
}
