package com.mtkj.mylvyou.net.http;

import com.mtkj.mylvyou.net.evnt.UserLoginBaen;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/8.
 */

public interface Userhttp {
    @POST("/user/userlogin!action")
    Call<UserLoginBaen> userlogin(@Query("mobile") String mobile,@Query("password") String password);

}
