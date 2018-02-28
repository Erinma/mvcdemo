package com.example.monthpractice1.contract;

import com.example.monthpractice1.bean.CatagoryBean;
import com.example.monthpractice1.bean.GroupGoodsBean;
import com.example.monthpractice1.utils.GsonObjectCallback;

/**
 * Created by mamiaomiao on 2018/2/27.
 * model层接口，用来操作数据，从网络获取数据；根据提供的接口分析，图一页面需要从网络请求两个接口，也就是获取数据的来源有两个，那就在model对应的接口里定义两个方法；
 */

public interface IShowCardModel {
    //请求商品子分类类别的数据接口，显示右侧列表
    void getRightData(String url, GsonObjectCallback<GroupGoodsBean> callback);
    //请求商品总分类接口，用于在图一的左侧列表上显示
    void getGoodsCard(String url, GsonObjectCallback<CatagoryBean> callback);
}
