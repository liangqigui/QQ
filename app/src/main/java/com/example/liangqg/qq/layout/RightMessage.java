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

public class RightMessage extends LinearLayout {
    public RightMessage(Context context, AttributeSet attrs,String message) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.send_message, this);
        TextView messagetext=(TextView)findViewById(R.id.messagetext);
        RoundedImageView messagehead=(RoundedImageView)findViewById(R.id.messagehead);
        messagehead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"去到我的详情页",Toast.LENGTH_LONG).show();
            }
        });
        messagetext.setText(message);

    }
}
