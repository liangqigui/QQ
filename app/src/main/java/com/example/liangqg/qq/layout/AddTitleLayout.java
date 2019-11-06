package com.example.liangqg.qq.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.liangqg.qq.R;

public class AddTitleLayout extends LinearLayout {
    private RelativeLayout main_RL;
    private LinearLayout main_add;
    private ImageView add_title_back;
    public AddTitleLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.add_title, this);
        add_title_back=(ImageView)findViewById(R.id.add_title_back);
        add_title_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                main_RL.setVisibility(View.VISIBLE);
                main_add.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void setRelativeLayout(RelativeLayout main_RL,LinearLayout main_add){
        this.main_add=main_add;
        this.main_RL=main_RL;
    }
}
