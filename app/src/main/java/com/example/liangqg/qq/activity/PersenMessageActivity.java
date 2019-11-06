package com.example.liangqg.qq.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.layout.LeftMessage;
import com.example.liangqg.qq.layout.MessageLayout_Title;
import com.example.liangqg.qq.layout.RightMessage;

public class PersenMessageActivity extends BaseActivity {
private EditText messageEditText;
private Button send_message;
private LinearLayout messageList;
private RelativeLayout person_message_RL;
private boolean flag=false;
private MessageLayout_Title messageLayout_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_persen_message);
        messageEditText=(EditText)findViewById(R.id.messageEditText);
        send_message=(Button)findViewById(R.id.send_message);
        messageList=(LinearLayout)findViewById(R.id.messageList);
        person_message_RL=(RelativeLayout)findViewById(R.id.person_message_RL);
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=messageEditText.getText().toString();
                LinearLayout.LayoutParams lLayoutlayoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                if (!message.equals("")){
                    if(flag){
                        RightMessage  rightMessage=new RightMessage(PersenMessageActivity.this,
                                null,message );
                        rightMessage.setLayoutParams(lLayoutlayoutParams);
                        messageList.addView(rightMessage);
                        flag=false;
                    }else {
                        LeftMessage leftMessage=new LeftMessage(PersenMessageActivity.this,
                                null,message);
                        leftMessage.setLayoutParams(lLayoutlayoutParams);
                        messageList.addView(leftMessage);
                        flag=true;
                    }
                    messageEditText.setText("");
                }
            }
        });
    }
}
