package com.example.liangqg.qq.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.liangqg.qq.R;

public class DynamicLayout extends LinearLayout {
    public DynamicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.dynamic, this);
    }
}
