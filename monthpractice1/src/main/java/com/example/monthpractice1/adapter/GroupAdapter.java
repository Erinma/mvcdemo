package com.example.monthpractice1.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monthpractice1.R;
import com.example.monthpractice1.bean.GroupGoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/27.
 * 图一右侧嵌套recyclerview的适配器
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private List<GroupGoodsBean.DataBean> list = new ArrayList<>();
    private Context context;


    public GroupAdapter(Context context ) {
        this.context = context;
    }
    //刷新数据
public void updateData(List<GroupGoodsBean.DataBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
}
    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_item, null);

        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        //网格recyclerview的数据展示
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        MyAdapter adapter = new MyAdapter(context);
        //为图一嵌套的recyclerview设置适配器
        holder.recyclerView.setAdapter(adapter);
        adapter.addData(list.get(position).getList());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RecyclerView recyclerView;

        public GroupViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_list);
        }
    }
}
