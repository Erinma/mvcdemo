package com.example.monthpractice1.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.monthpractice1.R;
import com.example.monthpractice1.adapter.CardAdapter;
import com.example.monthpractice1.bean.CardBean;
import com.example.monthpractice1.contract.ICardView;
import com.example.monthpractice1.presenter.CardPresenter;

import java.util.List;

/**
 * Created by mamiaomiao on 2018/3/1.
 */

public class ThirdListActivity extends Activity implements ICardView {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private CardPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        recyclerView = (RecyclerView) findViewById(R.id.third_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ThirdListActivity.this));
        presenter = new CardPresenter();
        presenter.attachView(this);
        presenter.showCardList();

    }

    @Override
    public void showCardList(List<CardBean.DataBean> dataBeans) {
        adapter = new CardAdapter(dataBeans, ThirdListActivity.this);
        recyclerView.setAdapter(adapter);
    }
}
