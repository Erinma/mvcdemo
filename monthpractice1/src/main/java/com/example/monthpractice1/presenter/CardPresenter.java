package com.example.monthpractice1.presenter;

import com.example.monthpractice1.bean.CardBean;
import com.example.monthpractice1.contract.ICardView;
import com.example.monthpractice1.model.CardModel;
import com.example.monthpractice1.utils.GsonObjectCallback;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;

/**
 * Created by mamiaomiao on 2018/3/1.
 * 购物车逻辑实现
 */

public class CardPresenter {
    private CardModel model;
    private WeakReference<ICardView> reference;

    public CardPresenter() {
        this.model = new CardModel();
    }

    public void attachView(ICardView view) {
        reference = new WeakReference<ICardView>(view);
    }

    public void detachView() {
        if (reference != null) {
            reference.clear();
            reference = null;
        }
    }

    public void showCardList() {
        model.getCardList("https://www.zhaoapi.cn/product/getCarts?uid=4582", new GsonObjectCallback<CardBean>() {
            @Override
            public void onUi(CardBean cardBean) {
                List<CardBean.DataBean> list=cardBean.getData();
                if(list!=null){
                    for(int i=0;i<list.size();i++){
                        CardBean.DataBean bean=list.get(i);
                        if(bean.getList()==null||bean.getList().size()<=0){
                            list.remove(i);
                            i--;
                        }
                    }
                }
                reference.get().showCardList(list);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

}
