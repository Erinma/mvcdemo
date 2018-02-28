package com.example.monthpractice1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.monthpractice1.R;
import com.example.monthpractice1.adapter.GroupAdapter;
import com.example.monthpractice1.adapter.LeftAdapter;
import com.example.monthpractice1.adapter.OnItemClickListner;
import com.example.monthpractice1.bean.CatagoryBean;
import com.example.monthpractice1.bean.GroupGoodsBean;
import com.example.monthpractice1.contract.IShowCardView;
import com.example.monthpractice1.presenter.ShowGoodsPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IShowCardView {
    private RecyclerView leftList, rightList;
    private LeftAdapter adapter;
    private GroupAdapter groupAdapter;
    private ShowGoodsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftList = (RecyclerView) findViewById(R.id.left_list);
        rightList = (RecyclerView) findViewById(R.id.right_list);
        //设置布局管理器
        leftList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rightList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //初始化右侧列表对应的adapter，设置给右侧列表
        groupAdapter = new GroupAdapter(MainActivity.this);
        rightList.setAdapter(groupAdapter);
        //实例化presenter；
        presenter = new ShowGoodsPresenter();
        //初始化presenter对应的view
        presenter.attachView(this);
        //开始调用presenter内定义的方法，展示左侧列表数据，并更新显示右侧列表数据；
        presenter.getGoodsList();

    }

    @Override
    public void showRightData(List<GroupGoodsBean.DataBean> data) {
        groupAdapter.updateData(data);

    }

    //展示左侧列表
    @Override
    public void showCardData(List<CatagoryBean.DataBean> data) {
        LeftAdapter adapter = new LeftAdapter(MainActivity.this, data);
        leftList.setAdapter(adapter);
        adapter.setClick(new OnItemClickListner() {
            @Override
            public void onItemClick(View view, int cid) {
                //左侧列表的条目点击事件；
                presenter.showRightList(cid);
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }
}
