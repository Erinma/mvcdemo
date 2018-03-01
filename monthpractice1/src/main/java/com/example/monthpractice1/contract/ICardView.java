package com.example.monthpractice1.contract;

import com.example.monthpractice1.bean.CardBean;

import java.util.List;

/**
 * Created by mamiaomiao on 2018/3/1.
 * 购物车视图
 */

public interface ICardView {
    void showCardList(List<CardBean.DataBean> dataBeans);
}
