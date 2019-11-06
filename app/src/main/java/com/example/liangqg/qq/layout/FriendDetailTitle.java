package com.example.liangqg.qq.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.activity.BaseActivity;
import com.example.liangqg.qq.activity.FriendDetailActivity;
import com.example.liangqg.qq.activity.PersenMessageActivity;

public class FriendDetailTitle  extends LinearLayout {
    private ImageView friend_detail_back;
    public FriendDetailTitle(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.friend_detail_title, this);
        friend_detail_back=(ImageView)findViewById(R.id.friend_detail_back);
        friend_detail_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),PersenMessageActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
