package com.example.liangqg.qq.layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.activity.BaseActivity;
import com.example.liangqg.qq.activity.FriendDetailActivity;
import com.example.liangqg.qq.activity.LoginActivity;
import com.example.liangqg.qq.activity.MainActivity;
import com.makeramen.roundedimageview.RoundedImageView;

public class MessageLayout_Title extends LinearLayout {
    public MessageLayout_Title(final Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.message_title, this);
        Button back=(Button)findViewById(R.id.message_back);
        RoundedImageView head=(RoundedImageView)findViewById(R.id.message_head);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                context.startActivity(intent);
            }
        });
        head.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),FriendDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
