package com.example.monthpractice1.model;

import com.example.monthpractice1.bean.GoodsBean;
import com.example.monthpractice1.utils.GsonObjectCallback;
import com.example.monthpractice1.utils.OkhttpUtil;

/**
 * Created by mamiaomiao on 2018/2/28.
 * 图二对应的model层，获取子分类商品列表数据
 */

public class GoodsListShowModel {

    public void getGoodsList(String url, GsonObjectCallback<GoodsBean> callback) {
        OkhttpUtil.doGet(url, callback);

    }
}
