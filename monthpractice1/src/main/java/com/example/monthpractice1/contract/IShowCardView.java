package com.example.monthpractice1.contract;

import com.example.monthpractice1.bean.CatagoryBean;
import com.example.monthpractice1.bean.GroupGoodsBean;

import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/27.
 * view层的接口，分析图一效果图可知，由左右两个列表组成；左侧是一个垂直方向的recyclerview（item只有文本）
 * ，右侧也是一个垂直方向的列表（item包括一个文本+网格列表（item包括一个图片+文本））；
 * 所以需要用到两个方法来做此两个view的数据显示；
 */

public interface IShowCardView {
    //给右侧list展示数据（给右侧recyclerview设置adapter并加载数据）
    void showRightData(List<GroupGoodsBean.DataBean> data);

    //给左侧list展示数据（给左侧recyclerview设置adapter并加载数据）
    void showCardData(List<CatagoryBean.DataBean> data);

    //报错信息的展示
    void showError(String error);
}
