package com.example.monthpractice1.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.monthpractice1.R;
import com.example.monthpractice1.bean.CardBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mamiaomiao on 2018/3/1.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardBean.DataBean> dataBeans = new ArrayList<>();
    private Context context;
    //存储勾选标志
    private List<Integer> tags = new ArrayList<>();
    private Map<Integer,Double> map=new HashMap<>();

    public CardAdapter(List<CardBean.DataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }
    private CardItemAdapter.OnCheckListner listner;

    public void setSellerCheck(CardItemAdapter.OnCheckListner listner) {
        this.listner = listner;
    }
    //设置activity界面操作全选，反选按钮作用适配器
    public void setAllGoodsCheck(boolean ischeck) {
        if (ischeck) {
            tags.clear();
            for (int i = 0; i < dataBeans.size(); i++) {
                tags.add(i);
            }
        }else if(tags.size()==dataBeans.size()){
            tags.clear();
        }
        notifyDataSetChanged();
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_layout, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        holder.checkBox.setChecked(tags.contains(position));
        holder.title.setText(dataBeans.get(position).getSellerName());
        if(holder.recyclerView.getAdapter()==null){
        final CardItemAdapter adapter = new CardItemAdapter(context, dataBeans.get(position).getList(),tags.contains(position));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter.setSellerCheck(new CardItemAdapter.OnCheckListner() {
            @Override
            public void setCheck(boolean isCheck) {
                holder.checkBox.setChecked(isCheck);
            }

            @Override
            public void getTotal(double item) {
               map.put(position,item);
               double price=0;
               for(int i=0;i<dataBeans.size();i++){
                   if(map.get(i)!=null){
                       price+=map.get(i);
                   }
               }
               listner.getTotal(price);
            }
        });
        //商家勾选作用于所有商品
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               //影响内部recyclerview
                adapter.setAllGoodsCheck(isChecked);



                //影响外部activity的全选按钮
                if (isChecked) {//选中，将position放入
                    if (!tags.contains(position)) {
                        tags.add(position);
                    }
                } else if (tags.contains(position)) {
                    tags.remove((Integer) position);
                }
                if (tags.size() == dataBeans.size()) {//代表当前商家选中所有商品
                    listner.setCheck(true);
                } else {//代表商家没有商品被选中
                    listner.setCheck(false);
                }
            }
        });}

    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView title;
        private RecyclerView recyclerView;

        public CardViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.card_sellercheck);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.card_list);
            title = (TextView) itemView.findViewById(R.id.card_sellername);
        }
    }
}
