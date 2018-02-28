package com.example.monthpractice1.presenter;

import com.example.monthpractice1.bean.CatagoryBean;
import com.example.monthpractice1.bean.GroupGoodsBean;
import com.example.monthpractice1.contract.IShowCardView;
import com.example.monthpractice1.model.ShowCardModel;
import com.example.monthpractice1.utils.GsonObjectCallback;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;

/**
 * Created by mamiaomiao on 2018/2/27.
 * presenter负责业务逻辑处理，是view层与model层的纽带；
 */

public class ShowGoodsPresenter {
    //防止内存泄漏，将view的对象使用弱引用来管理
    private WeakReference<IShowCardView> reference;
    private ShowCardModel model;

    //presenter类构造方法，获取model类实例；
    public ShowGoodsPresenter() {
        model = new ShowCardModel();
    }

    public void attachView(IShowCardView view) {
        reference = new WeakReference<IShowCardView>(view);
    }

    public void detachView(IShowCardView view) {
        if (reference != null) {
            reference.clear();
            reference = null;
        }
    }

    //presenter类里自定义的方法，实现业务逻辑
    public void getGoodsList() {
        //获取图一左侧分类列表数据并展示
        model.getGoodsCard("https://www.zhaoapi.cn/product/getCatagory", new GsonObjectCallback<CatagoryBean>() {
            @Override
            public void onUi(CatagoryBean o) {
                //图一左侧分类列表加载完成以后，右侧默认显示第一个分类对应的商品子分类列表
                if (o != null && o.getData() != null) {
                    List<CatagoryBean.DataBean> bean = o.getData();
                    if (bean != null) {
                        //reference.get()获取view层对应接口的实例
                        //显示左侧列表数据
                        reference.get().showCardData(bean);
                        //如果左侧数据不为空，右侧默认显示对应左侧第一条数据的子分类；
                        if (bean.size() > 0) {
                            getRightData(bean.get(0).getCid());
                        }
                    } else {
                        reference.get().showError("数据为空");
                    }
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {
                reference.get().showError(e.getMessage());
            }
        });

    }

    //获取图一右侧列表数据并展示
    public void showRightList(int cid) {
        getRightData(cid);
    }

    //请求右侧列表数据
    private void getRightData(int cid) {
        model.getRightData("https://www.zhaoapi.cn/product/getProductCatagory?cid="
                + cid, new GsonObjectCallback<GroupGoodsBean>() {
            @Override
            public void onUi(GroupGoodsBean groupGoodsBean) {
                if (groupGoodsBean != null && groupGoodsBean.getData() != null) {
                    reference.get().showRightData(groupGoodsBean.getData());
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }


}
