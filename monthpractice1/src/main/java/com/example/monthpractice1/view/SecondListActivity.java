package com.example.monthpractice1.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.example.monthpractice1.R;
import com.example.monthpractice1.adapter.GoodsAdapter;
import com.example.monthpractice1.bean.GoodsBean;
import com.example.monthpractice1.contract.IGoodsListView;
import com.example.monthpractice1.presenter.GoodsListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/28.
 */

public class SecondListActivity extends Activity implements IGoodsListView {
    private XRecyclerView recyclerView;
    private GoodsAdapter adapter;
    private GoodsListPresenter presenter;
    private int pscid;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //从上一个列表页（图一）获取pscid
        Intent intent = getIntent();
        if (intent != null) {
            pscid = intent.getIntExtra("pscid", 0);
        }
        //隐藏左侧列表，只显示右侧列表
        //findViewById(R.id.left_list).setVisibility(View.GONE);
        recyclerView = (XRecyclerView) findViewById(R.id.xlist);
        adapter = new GoodsAdapter(SecondListActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SecondListActivity.this));
//实例化presenter,关联view
        presenter = new GoodsListPresenter();
        presenter.attachView(this);
        //通过presenter请求数据并显示；
        if (pscid > 0) {
            presenter.getData(pscid, page);
        }

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getData(pscid, page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getData(pscid, page);
            }
        });
    }

    @Override
    public void showGoodsList(List<GoodsBean.DataBean> data) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (page > 1) {
            //显示添加数据
            adapter.addList(data);
        } else {
            adapter.updateList(data);
        }

    }
}
