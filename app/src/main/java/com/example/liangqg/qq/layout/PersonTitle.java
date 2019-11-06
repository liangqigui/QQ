package com.example.liangqg.qq.layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.activity.BaseActivity;
import com.example.liangqg.qq.activity.MainActivity;
public class PersonTitle extends LinearLayout {
    private ImageView person_detail_back;
    public PersonTitle(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.person_title, this);
        person_detail_back=(ImageView)findViewById(R.id.person_detail_back);
        person_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
