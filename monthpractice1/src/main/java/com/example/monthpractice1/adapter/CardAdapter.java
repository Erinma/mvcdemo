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
import java.util.List;

/**
 * Created by mamiaomiao on 2018/3/1.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<CardBean.DataBean> dataBeans = new ArrayList<>();
    private Context context;

    public CardAdapter(List<CardBean.DataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_layout, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        //holder.checkBox.setChecked(true);

        holder.title.setText(dataBeans.get(position).getSellerName());
        final CardItemAdapter adapter = new CardItemAdapter(context, dataBeans.get(position).getList());
        holder.recyclerView.setAdapter(adapter);
        adapter.setSellerCheck(new CardItemAdapter.OnCheckListner() {
            @Override
            public void setCheck(boolean isCheck) {
                holder.checkBox.setChecked(isCheck);
            }
        });
        //商家勾选作用于所有商品
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setAllGoodsCheck(isChecked);
            }
        });
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
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
