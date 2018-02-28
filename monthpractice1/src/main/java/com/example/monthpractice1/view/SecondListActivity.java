package com.example.monthpractice1.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.monthpractice1.R;
import com.example.monthpractice1.adapter.GoodsAdapter;
import com.example.monthpractice1.bean.GoodsBean;
import com.example.monthpractice1.contract.IGoodsListView;
import com.example.monthpractice1.presenter.GoodsListPresenter;

import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/28.
 */

public class SecondListActivity extends Activity implements IGoodsListView {
    private RecyclerView recyclerView;
    private GoodsAdapter adapter;
    private GoodsListPresenter presenter;
    private int pscid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从上一个列表页（图一）获取pscid
        Intent intent = getIntent();
        if (intent != null) {
            pscid = intent.getIntExtra("pscid", 0);
        }
        //隐藏左侧列表，只显示右侧列表
        findViewById(R.id.left_list).setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.right_list);
        adapter = new GoodsAdapter(SecondListActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SecondListActivity.this));
//实例化presenter,关联view
        presenter = new GoodsListPresenter();
        presenter.attachView(this);
        //通过presenter请求数据并显示；
        if (pscid > 0) {
            presenter.getData(pscid, 1);
        }
    }

    @Override
    public void showGoodsList(List<GoodsBean.DataBean> data) {
        //显示数据
        adapter.addList(data);
    }
}
