package com.example.monthpractice1.model;

import com.example.monthpractice1.bean.CatagoryBean;
import com.example.monthpractice1.bean.GroupGoodsBean;
import com.example.monthpractice1.contract.IShowCardModel;
import com.example.monthpractice1.utils.GsonObjectCallback;
import com.example.monthpractice1.utils.OkhttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by mamiaomiao on 2018/2/27.
 * model负责  数据   处理；
 */

public class ShowCardModel implements IShowCardModel {
    //请求商品分类接口
    @Override
    public void getGoodsCard(String url,GsonObjectCallback callback) {
        OkhttpUtil.getInstance().doGet(url, callback);

    }
//请求商品 子 分类的网络接口
    @Override
    public void getRightData(String url, GsonObjectCallback<GroupGoodsBean> callback) {
        OkhttpUtil.getInstance().doGet(url,callback);
    }
}
