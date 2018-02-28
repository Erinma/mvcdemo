package com.example.monthpractice1.presenter;

import com.example.monthpractice1.bean.GoodsBean;
import com.example.monthpractice1.contract.IGoodsListView;
import com.example.monthpractice1.model.GoodsListShowModel;
import com.example.monthpractice1.utils.GsonObjectCallback;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;

/**
 * Created by mamiaomiao on 2018/2/28.
 * 对应图二业务逻辑层presenter
 */

public class GoodsListPresenter {
    private GoodsListShowModel model;
    private WeakReference<IGoodsListView> reference;

    //构造方法
    public GoodsListPresenter() {
        model = new GoodsListShowModel();
    }
//关联view
    public void attachView(IGoodsListView view){
        reference=new WeakReference<IGoodsListView>(view);
    }
    //防止内存泄漏，创建view解绑
    public void detachView(){
        if(reference!=null){
            reference.clear();
            reference=null;
        }
    }

    public void getData(int pscid, int page) {
        model.getGoodsList("https://www.zhaoapi.cn/product/getProducts?pscid=" + pscid + "&page=" + page, new GsonObjectCallback<GoodsBean>() {
            @Override
            public void onUi(GoodsBean goodsBean) {
                //数据请求成功，显示到view上
            reference.get().showGoodsList(goodsBean.getData());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

}
