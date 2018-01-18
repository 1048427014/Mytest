package com.mtkj.mylvyou.net.evnt;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 */

public class UserLoginBaen {


    /**
     * Rt : 1
     * Msg : 登录成功
     * Res : [{"addip":"127.0.0.1","address":"浙江台州路桥路南街道","addtime":"1","age":"16","area":"台州","attention":"2","censoring":"1","click":"0","id":"1","integral":"0","lang":"1","loginip":"127.0.0.1","loginnumber":"10","logintime":"2017-12-30 16:29:29","mail":"1205615021@q.com","mobile":"15157269365","money":"0","monicker":"鸭子","name":"廖聪","openid":null,"password":"123456","portrait":"www.baidu.com","qq":"1048427014","recommend":"0","sex":"1","state":"1","uid":null,"unionid":null,"wechat":"wx1205615021"}]
     */

    private int Rt;
    private String Msg;
    private List<ResBean> Res;

    public int getRt() {
        return Rt;
    }

    public void setRt(int Rt) {
        this.Rt = Rt;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public List<ResBean> getRes() {
        return Res;
    }

    public void setRes(List<ResBean> Res) {
        this.Res = Res;
    }

    public static class ResBean {
        /**
         * addip : 127.0.0.1
         * address : 浙江台州路桥路南街道
         * addtime : 1
         * age : 16
         * area : 台州
         * attention : 2
         * censoring : 1
         * click : 0
         * id : 1
         * integral : 0
         * lang : 1
         * loginip : 127.0.0.1
         * loginnumber : 10
         * logintime : 2017-12-30 16:29:29
         * mail : 1205615021@q.com
         * mobile : 15157269365
         * money : 0
         * monicker : 鸭子
         * name : 廖聪
         * openid : null
         * password : 123456
         * portrait : www.baidu.com
         * qq : 1048427014
         * recommend : 0
         * sex : 1
         * state : 1
         * uid : null
         * unionid : null
         * wechat : wx1205615021
         */

        private String addip;
        private String address;
        private String addtime;
        private String age;
        private String area;
        private String attention;
        private String censoring;
        private String click;
        private String id;
        private String integral;
        private String lang;
        private String loginip;
        private String loginnumber;
        private String logintime;
        private String mail;
        private String mobile;
        private String money;
        private String monicker;
        private String name;
        private Object openid;
        private String password;
        private String portrait;
        private String qq;
        private String recommend;
        private String sex;
        private String state;
        private Object uid;
        private Object unionid;
        private String wechat;

        public String getAddip() {
            return addip;
        }

        public void setAddip(String addip) {
            this.addip = addip;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getCensoring() {
            return censoring;
        }

        public void setCensoring(String censoring) {
            this.censoring = censoring;
        }

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getLoginip() {
            return loginip;
        }

        public void setLoginip(String loginip) {
            this.loginip = loginip;
        }

        public String getLoginnumber() {
            return loginnumber;
        }

        public void setLoginnumber(String loginnumber) {
            this.loginnumber = loginnumber;
        }

        public String getLogintime() {
            return logintime;
        }

        public void setLogintime(String logintime) {
            this.logintime = logintime;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMonicker() {
            return monicker;
        }

        public void setMonicker(String monicker) {
            this.monicker = monicker;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getUid() {
            return uid;
        }

        public void setUid(Object uid) {
            this.uid = uid;
        }

        public Object getUnionid() {
            return unionid;
        }

        public void setUnionid(Object unionid) {
            this.unionid = unionid;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
