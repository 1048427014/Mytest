package com.mtkj.mylvyou.net.evnt;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */

public class TestEvnet {


    /**
     * astatus : 1
     * members : [{"mobile":"13738503998","monicker":"萌萌哒","name":"罗超"},{"mobile":"13738503999","monicker":"皮皮迪","name":"赵科迪"}]
     */

    private int astatus;
    private List<MembersBean> members;

    public int getAstatus() {
        return astatus;
    }

    public void setAstatus(int astatus) {
        this.astatus = astatus;
    }

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class MembersBean {
        /**
         * mobile : 13738503998
         * monicker : 萌萌哒
         * name : 罗超
         */

        private String mobile;
        private String monicker;
        private String name;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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
    }
}
