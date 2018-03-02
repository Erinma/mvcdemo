package com.example.monthpractice1.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.monthpractice1.R;
import com.example.monthpractice1.adapter.CardItemAdapter;

/**
 * Created by mamiaomiao on 2017/10/30.
 */

public class PlusReduceView extends LinearLayout implements View.OnClickListener, TextWatcher {
    private TextView plus, reduce;
    private EditText sumEdit;
    private int totalsum = 1;

    public PlusReduceView(Context context) {
        super(context);
        init(context);
    }

    public PlusReduceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlusReduceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.plus_add_view, this, true);
        plus = (TextView) findViewById(R.id.view_plus);
        reduce = (TextView) findViewById(R.id.view_reduce);
        sumEdit = (EditText) findViewById(R.id.view_sum);
        plus.setOnClickListener(this);
        reduce.setOnClickListener(this);
        sumEdit.addTextChangedListener(this);
    }

    private CardItemAdapter.OnChangeListner listner;

    public void setLinster(CardItemAdapter.OnChangeListner linster) {
        this.listner = linster;
    }

    //初始化数据；
    public void initData(int num) {
        totalsum = num;
        sumEdit.setText(totalsum + "");
        System.out.println(num);
        this.listner.getSum(totalsum);
    }

    public int getSumData() {
        return totalsum;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_plus:
                //获取输入框里的数字
                getSum();
                //+1
                initData(totalsum += 1);
                break;
            case R.id.view_reduce:
                //点击减号，获取输入框里的值，-1
                getSum();
                //先判断是否小于最小值1；如果>1才执行减法，否则不变；
                if (totalsum <= 1) {

                } else {
                    initData(totalsum -= 1);
                }
                break;

            default:
                break;
        }
    }

    private void getSum() {
        String text = sumEdit.getText().toString().trim();
        totalsum = Integer.parseInt(text);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            initData(1);
            sumEdit.setSelection(1);
        } else {
            if (!editable.toString().equals(totalsum + "")) {
                getSum();
                initData(totalsum);
                sumEdit.setSelection((totalsum + "").length());
            }
        }
    }
}
