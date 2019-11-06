package com.example.liangqg.qq.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.util.Dic;
import com.example.liangqg.qq.util.UserToServer;

import java.io.IOException;

public class LoginActivity extends BaseActivity {
private ImageView lock;
private EditText passwordET,accountET;
private Button register,login;
private CheckBox remenberPassword;
private boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        lock=(ImageView)findViewById(R.id.lock);
        passwordET=(EditText)findViewById(R.id.password);
        accountET=(EditText)findViewById(R.id.account);
        register=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        remenberPassword=(CheckBox)findViewById(R.id.remenberPassword);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=!flag;
                if(flag){
                    lock.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    lock.setImageResource(R.drawable.ic_visibility_black_24dp);
                    passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    passwordET.setTypeface(Typeface.DEFAULT);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String account=accountET.getText().toString();
                final String password=passwordET.getText().toString();
                final String url= Dic.SERVER_IP_PORT+ Dic.USER_LOGIN;
                        final Handler myHandler = new Handler(){
                            public void handleMessage(Message msg){
                                String responseResult = (String)msg.obj;
                                //登录成功
                                if(responseResult.equals("true")){
                                   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                   startActivity(intent);
                                }
                                //登录失败
                                else{
                                    Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                                }
                            }
                        };
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                UserToServer userToServer = new UserToServer();
                                try {
                                    String result = userToServer.login(account, password,url);
                                    Message msg = new Message();
                                    msg.obj = result;
                                    myHandler.sendMessage(msg);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
