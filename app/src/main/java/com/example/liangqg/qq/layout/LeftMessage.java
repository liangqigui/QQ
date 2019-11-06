package com.example.liangqg.qq.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangqg.qq.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class LeftMessage extends LinearLayout {
    public LeftMessage(Context context, AttributeSet attrs,String message) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.receive_message, this);
        TextView messageTextView=(TextView)findViewById(R.id.messagetext);
        RoundedImageView head=(RoundedImageView)findViewById(R.id.messagehead);
        head.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"去到好友详情页",Toast.LENGTH_LONG).show();
            }
        });
        messageTextView.setText(message);
    }
}
