package com.example.monthpractice1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.monthpractice1.R;
import com.example.monthpractice1.bean.CardBean;
import com.example.monthpractice1.view.PlusReduceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamiaomiao on 2018/3/1.
 * 购物车列表适配器对应商品列表的子适配器
 */

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder> {
    private List<CardBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;
    //存储勾选标志
    private List<Integer> tags = new ArrayList<>();

    public CardItemAdapter(Context context, List<CardBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;

    }

    private OnCheckListner listner;

    public void setSellerCheck(OnCheckListner listner) {
        this.listner = listner;
    }

    public void setAllGoodsCheck(boolean ischeck) {
        tags.clear();
        if (ischeck) {
            for (int i = 0; i < list.size(); i++) {
                tags.add(i);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public CardItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_item, parent, false);
        CardItemViewHolder holder = new CardItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CardItemViewHolder holder, final int position) {
        //初始化checkbox状态
        holder.checkBox.setChecked(tags.contains(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//选中，将position放入
                    if (!tags.contains(position)) {
                        tags.add(position);
                    }
                } else if (tags.contains(position)) {
                    tags.remove((Integer) position);
                }
                if (tags.size() == list.size()) {//代表当前商家选中所有商品
                    listner.setCheck(true);
                } else if(tags.size() ==0){//代表商家没有商品被选中
                    listner.setCheck(false);
                }

            }
        });
        String url = list.get(position).getImages();
        String[] urls = url.split("\\|");
        if (urls != null && urls.length > 0) {
            url = urls[0];
        }
        Glide.with(context).load(url).into(holder.img);
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("优惠价：¥" + list.get(position).getBargainPrice());
        holder.num.initData(list.get(position).getNum());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CardItemViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView img;
        private TextView title, price;
        private PlusReduceView num;

        public CardItemViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.card_goodscheck);
            img = (ImageView) itemView.findViewById(R.id.card_goodsimg);
            title = (TextView) itemView.findViewById(R.id.card_goodsname);
            price = (TextView) itemView.findViewById(R.id.card_goodsprice);
            num = (PlusReduceView) itemView.findViewById(R.id.card_goodsnum);
        }
    }

    public interface OnCheckListner {
        void setCheck(boolean isCheck);
    }
}
