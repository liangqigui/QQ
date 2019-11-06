package com.example.liangqg.qq.layout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.liangqg.qq.R;

public class TitleLayout extends LinearLayout {
private RelativeLayout main_RL;
private LinearLayout main_add;
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this);
        Button add =(Button)findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                main_RL.setVisibility(View.INVISIBLE);
                main_add.setVisibility(View.VISIBLE);
            }
        });
    }
    public void setRelativeLayout(RelativeLayout main_RL,LinearLayout main_add){
        this.main_add=main_add;
        this.main_RL=main_RL;
    }
}
