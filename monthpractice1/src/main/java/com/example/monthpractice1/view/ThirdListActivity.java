package com.example.monthpractice1.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.monthpractice1.R;
import com.example.monthpractice1.adapter.CardAdapter;
import com.example.monthpractice1.adapter.CardItemAdapter;
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
    private CheckBox checkBox;
    private TextView textView;
    private Button button;
    private double price=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        recyclerView = (RecyclerView) findViewById(R.id.third_list);
        checkBox = (CheckBox) findViewById(R.id.all_check);
        textView = (TextView) findViewById(R.id.total_price);
        recyclerView.setLayoutManager(new LinearLayoutManager(ThirdListActivity.this));
        presenter = new CardPresenter();
        presenter.attachView(this);
        presenter.showCardList();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setAllGoodsCheck(isChecked);
            }
        });

    }

    @Override
    public void showCardList(List<CardBean.DataBean> dataBeans) {
        if (dataBeans != null) {
            adapter = new CardAdapter(dataBeans, ThirdListActivity.this);
            recyclerView.setAdapter(adapter);
            adapter.setSellerCheck(new CardItemAdapter.OnCheckListner() {
                @Override
                public void setCheck(boolean isCheck) {
                    checkBox.setChecked(isCheck);
                }

                @Override
                public void getTotal(double item) {
                    textView.setText(item+"");
                }
            });
        }
    }
}
