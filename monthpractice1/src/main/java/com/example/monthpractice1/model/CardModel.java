package com.example.monthpractice1.model;

import com.example.monthpractice1.bean.CardBean;
import com.example.monthpractice1.utils.GsonObjectCallback;
import com.example.monthpractice1.utils.OkhttpUtil;

/**
 * Created by mamiaomiao on 2018/3/1.
 * 从网络获取购物车列表数据
 */

public class CardModel {

    public void getCardList(String url, GsonObjectCallback<CardBean> callback) {
        OkhttpUtil.getInstance().doGet(url, callback);
    }
}
