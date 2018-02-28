package com.example.monthpractice1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.monthpractice1.R;
import com.example.monthpractice1.bean.GroupGoodsBean;
import com.example.monthpractice1.view.SecondListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/27.
 * 图一对应显示图文的适配器
 */

public class MyAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private ArrayList<GroupGoodsBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<GroupGoodsBean.DataBean.ListBean> data) {
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        final TextViewHolder holder = new TextViewHolder(view);
        //为图文item添加条目点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式一：直接点击跳转
                context.startActivity(new Intent(context, SecondListActivity.class)
                        .putExtra("pscid", list.get(holder.getLayoutPosition()).getPscid()));

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
