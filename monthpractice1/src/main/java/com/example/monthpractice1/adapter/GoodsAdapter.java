package com.example.monthpractice1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.monthpractice1.R;
import com.example.monthpractice1.bean.GoodsBean;
import com.example.monthpractice1.view.ThirdListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamiaomiao on 2018/2/28.
 * 图二列表展示对应的适配器
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private Context context;
    private List<GoodsBean.DataBean> data = new ArrayList<>();

    public GoodsAdapter(Context context) {
        this.context = context;
    }

    //刷新列表数据
    public void updateList(List<GoodsBean.DataBean> data) {
        this.data.clear();
        addList(data);
    }

    //加载下一页数据
    public void addList(List<GoodsBean.DataBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods_list, parent, false);
        GoodsViewHolder holder = new GoodsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
//设置item显示的数据
        holder.title.setText(data.get(position).getTitle());
        holder.price.setText("原价：¥"+data.get(position).getPrice());
        holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        //holder.pricenow.setText("优惠价：¥"+data.get(position).getBargainPrice());
   holder.pricenow.setText(String.format(context.getString(R.string.goods_price),data.get(position).getBargainPrice()+""));
    //设置图片
        String url=data.get(position).getImages();
        String[] urls=url.split("\\|");
        if(urls!=null&&urls.length>0){
            url=urls[0];
        }
        Glide.with(context).load(url).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ThirdListActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, pricenow;
        public ImageView img;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_goods_title);
            price = (TextView) itemView.findViewById(R.id.item_goods_price);
            pricenow = (TextView) itemView.findViewById(R.id.item_goods_pricenow);
            img = (ImageView) itemView.findViewById(R.id.item_goods_img);
        }
    }

}
