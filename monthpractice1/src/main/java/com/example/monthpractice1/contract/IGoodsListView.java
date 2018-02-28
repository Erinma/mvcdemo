package com.example.monthpractice1.contract;

import com.example.monthpractice1.bean.GoodsBean;

import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/28.
 * 图二view接口
 */

public interface IGoodsListView {
    void showGoodsList(List<GoodsBean.DataBean> data);
}
