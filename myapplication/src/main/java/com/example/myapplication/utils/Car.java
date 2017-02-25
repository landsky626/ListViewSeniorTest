package com.example.myapplication.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wjj on 2017/1/22.
 */

public class Car implements Serializable {

    /**
     * status : Ok
     * results : [{"namecn":"科伦","titleimg":"http://img.39.net/yy/2013/6/7/181c28df938.jpg"},{"namecn":"恒瑞","titleimg":"http://img.39.net/yy/2013/6/7/183837dd2c2.jpg"},{"namecn":"罗氏","titleimg":"http://img.39.net/yy/2013/10/29/2a3aee960c9.jpg"},{"namecn":"云南白药","titleimg":"http://img.39.net/yy/2013/6/7/18443142a2c.jpg"},{"namecn":"辉瑞","titleimg":"http://img.39.net/yy/2013/6/7/17d9910a1e2.jpg"},{"namecn":"拜耳","titleimg":"http://img.39.net/yy/2013/6/7/17fd3927b30.jpg"},{"namecn":"葛兰素史克","titleimg":"http://img.39.net/yy/2013/10/29/2ac804da630.jpg"},{"namecn":"阿斯利康","titleimg":"http://img.39.net/yy/2013/10/29/2a43e7706de.jpg"},{"namecn":"同仁堂","titleimg":"http://img.39.net/yy/2013/10/28/1e370fe0a72.jpg"},{"namecn":"诺华","titleimg":"http://img.39.net/yy/2013/6/7/17db1e6c47c.jpg"},{"namecn":"杭州默沙东","titleimg":"http://img.39.net/yy/2013/6/7/17ed8a4526e.jpg"},{"namecn":"赛诺菲安万特","titleimg":"http://img.39.net/yy/2013/6/7/17e82ae1ff3.jpg"},{"namecn":"步长","titleimg":"http://img.39.net/yy/2013/6/7/18286ab0f16.jpg"}]
     */

    private String status;
    private List<ResultsEntity> results;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity implements Serializable {
        /**
         * namecn : 科伦
         * titleimg : http://img.39.net/yy/2013/6/7/181c28df938.jpg
         */

        private String namecn;
        private String titleimg;

        public void setNamecn(String namecn) {
            this.namecn = namecn;
        }

        public void setTitleimg(String titleimg) {
            this.titleimg = titleimg;
        }

        public String getNamecn() {
            return namecn;
        }

        public String getTitleimg() {
            return titleimg;
        }
    }
}
