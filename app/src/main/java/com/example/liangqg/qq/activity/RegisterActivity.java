package com.example.liangqg.qq.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.bean.User;
import com.example.liangqg.qq.util.Dic;
import com.example.liangqg.qq.util.Format;
import com.example.liangqg.qq.util.UserToServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.UUID;

public class RegisterActivity extends BaseActivity {

    private byte[] bytes;
    private String account;
    private String email;
    private boolean phone_flag=false;
    public static final int COUNTDOWN_TIME_RESTART= 10000;
    public static final int COUNTDOWN_TIME_CODE = 99999;
    public static final int DELAY_MILLIS = 1000;
    public static final int MAX_COUNT = 10;

private Button checkphone,nextStep,verficationEmailButton,
        verficationCodetime,verficationEmailtime,registerNow,
        goToLogin;
private RelativeLayout check,next;
private LinearLayout check1,userMessage,firstStep,verficationEmail;
private EditText phoneVerficationCode,phoneET,editTextEmail,passwordRegister,
        passwordRegisterCertain,nameRegister,verficationCodeEmail;
private String phone;
private ImageView lockRegister1,lockRegister2,headRegister;

boolean flag1=false;
boolean flag2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        init();
        headRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else
                    openAlbum();
            }
        });
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nameRegister.getText().toString().trim();
                final String code=verficationCodeEmail.getText().toString().trim();
                String  passwordRegisterStr=passwordRegister.getText().toString().trim();
                String passwordRegisterCertainStr=passwordRegisterCertain.getText().toString().trim();

                if(passwordRegisterCertainStr.equals(passwordRegisterStr)&&
                        !passwordRegisterCertainStr.equals("")&&!passwordRegisterCertainStr.equals("")){
                    String imageName=UUID.randomUUID().toString().replace("-","");
                    final User user=new User();
                    user.setAccount(account);
                    user.setEmail(email);
                    user.setName(name);
                    user.setPassword(passwordRegisterCertainStr);
                    user.setBytes(bytes);
                    user.setImageName(imageName);
                    final String url= Dic.SERVER_IP_PORT+ Dic.USER_CHECKEMAIL;
                    final Handler myHandler = new Handler(){
                        public void handleMessage(Message msg){
                            String responseResult = (String)msg.obj;
                            if(responseResult.equals("true")){
                                final String url1= Dic.SERVER_IP_PORT+ Dic.USER_ADDUSER;
                                final Handler myHandler = new Handler(){
                                    public void handleMessage(Message msg){
                                        String responseResult = (String)msg.obj;
                                        if(responseResult.equals("true")){
                                            registerNow.setVisibility(View.GONE);
                                            goToLogin.setVisibility(View.VISIBLE);
                                        }
                                        else{
                                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                };
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        UserToServer userToServer = new UserToServer();
                                        try {
                                            String result = userToServer.addUser(user, url1);
                                            Message msg = new Message();
                                            msg.obj = result;
                                            myHandler.sendMessage(msg);

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "邮箱验证失败！", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserToServer userToServer = new UserToServer();
                            try {
                                String result = userToServer.checkEmailCode(code,email, url);
                                Message msg = new Message();
                                msg.obj = result;
                                myHandler.sendMessage(msg);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                }else {
                    Toast.makeText(RegisterActivity.this,
                            "两次密码不一致或为空!",Toast.LENGTH_LONG).show();
                }
            }
        });
        lockRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag1=!flag1;
                if(flag1){
                    lockRegister1.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    passwordRegister.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    lockRegister1.setImageResource(R.drawable.ic_visibility_black_24dp);
                    passwordRegister.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    passwordRegister.setTypeface(Typeface.DEFAULT);
                }
            }
        });
        lockRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag2=!flag2;
                if(flag2){
                    lockRegister2.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    passwordRegisterCertain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    lockRegister2.setImageResource(R.drawable.ic_visibility_black_24dp);
                    passwordRegisterCertain.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    passwordRegisterCertain.setTypeface(Typeface.DEFAULT);
                }
            }
        });
        verficationEmailtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verficationEmailtime.setEnabled(false);
                    verficationEmailtime.setText("5");
                    CountdownTimeHandler1 handler = new CountdownTimeHandler1(RegisterActivity.this);
                    String str = verficationEmailtime.getText().toString();
                    int time = Integer.parseInt(str);
                    Message message = Message.obtain();
                    message.what = COUNTDOWN_TIME_CODE;
                    message.arg1 = time;
                    handler.sendMessageDelayed(message, DELAY_MILLIS);

                final String url = Dic.SERVER_IP_PORT + Dic.USER_SENDEMAIL;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserToServer userToServer = new UserToServer();
                        try {
                            userToServer.sendEmail(email,url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        verficationCodetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                verficationCodetime.setEnabled(false);
                verficationCodetime.setText("5");
                        CountdownTimeHandler handler = new CountdownTimeHandler(RegisterActivity.this);
                        String str = verficationCodetime.getText().toString();
                        int time = Integer.parseInt(str);
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_CODE;
                        message.arg1 = time;
                        handler.sendMessageDelayed(message, DELAY_MILLIS);

                final String url = Dic.SERVER_IP_PORT + Dic.USER_SENDMESSAGE;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserToServer userToServer = new UserToServer();
                        try {
                            String result = userToServer.sendPhoneMessage(phone, url);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        verficationEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=editTextEmail.getText().toString().trim();
                if (Format.isEmail(email)){
                    verficationEmailtime.setEnabled(false);
                    verficationEmailButton.setVisibility(View.GONE);
                    verficationEmail.setVisibility(View.VISIBLE);

                    CountdownTimeHandler1 handler = new CountdownTimeHandler1(RegisterActivity.this);
                    String str = verficationEmailtime.getText().toString();
                    int time = Integer.parseInt(str);
                    Message message = Message.obtain();
                    message.what = COUNTDOWN_TIME_CODE;
                    message.arg1 = time;
                    handler.sendMessageDelayed(message, DELAY_MILLIS);

                final String url = Dic.SERVER_IP_PORT + Dic.USER_SENDEMAIL;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserToServer userToServer = new UserToServer();
                        try {
                             userToServer.sendEmail(email,url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }else
                Toast.makeText(RegisterActivity.this,"邮箱格式不对",Toast.LENGTH_LONG).show();
            }
        });
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String phoneVerficationCodeStr=phoneVerficationCode.getText().toString();
                final String url= Dic.SERVER_IP_PORT+ Dic.USER_CHECKCODE;

                final Handler myHandler = new Handler(){
                    public void handleMessage(Message msg){
                        String responseResult = (String)msg.obj;
                        //登录成功
                        if(responseResult.equals("true")){
                            firstStep.setVisibility(View.GONE);
                            userMessage.setVisibility(View.VISIBLE);
                        }
                        //登录失败
                        else{
                            Toast.makeText(RegisterActivity.this, "手机号码验证失败！", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserToServer userToServer = new UserToServer();
                        try {
                            String result = userToServer.checkPhoneCode(phoneVerficationCodeStr,account, url);
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
        checkphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone=phoneET.getText().toString().trim();
                if (Format.isChinaPhoneLegal(phone)){
                verficationCodetime.setEnabled(false);
                account=phone;
              check.removeViewAt(0);
              check1.setVisibility(View.VISIBLE);
              next.setVisibility(View.VISIBLE);

                    final String url = Dic.SERVER_IP_PORT + Dic.USER_SENDMESSAGE;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserToServer userToServer = new UserToServer();
                            try {
                                String result = userToServer.sendPhoneMessage(phone, url);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                CountdownTimeHandler handler = new CountdownTimeHandler(RegisterActivity.this);
                String str = verficationCodetime.getText().toString();
                int time = Integer.parseInt(str);
                Message message = Message.obtain();
                message.what = COUNTDOWN_TIME_CODE;
                message.arg1 = time;
                handler.sendMessageDelayed(message, DELAY_MILLIS);
            }else
                Toast.makeText(RegisterActivity.this,"请输入大陆号码",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        checkphone=(Button)findViewById(R.id.checkphone);
        check=(RelativeLayout)findViewById(R.id.check);
        nextStep=(Button)findViewById(R.id.nextbtn);
        next=(RelativeLayout)findViewById(R.id.next);
        check1=(LinearLayout)findViewById(R.id.check1);
        phoneET=(EditText)findViewById(R.id.phone);
        phoneVerficationCode=(EditText)findViewById(R.id.verficationCode);
        userMessage=(LinearLayout)findViewById(R.id.UserMessage);
        firstStep=(LinearLayout)findViewById(R.id.firstStep);
        nameRegister=(EditText)findViewById(R.id.nameRegister);
        verficationEmailButton=(Button)findViewById(R.id.verficationEmailButton);
        verficationEmail=(LinearLayout)findViewById(R.id.verficationEmail);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        verficationCodetime=(Button)findViewById(R.id.verficationCodetime);
        verficationEmailtime=(Button)findViewById(R.id.verficationEmailtime);
        lockRegister1=(ImageView)findViewById(R.id.lockRegister1);
        lockRegister2=(ImageView)findViewById(R.id.lockRegister2);
        passwordRegisterCertain=(EditText)findViewById(R.id.passwordRegisterCertain);
        passwordRegister=(EditText)findViewById(R.id.passwordRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        registerNow=(Button)findViewById(R.id.registerNow);
        goToLogin=(Button)findViewById(R.id.goToLogin);
        headRegister=(ImageView)findViewById(R.id.headRegister);
        verficationCodeEmail=(EditText)findViewById(R.id.verficationCodeEmail);
        nameRegister=(EditText)findViewById(R.id.nameRegister);
    }
    public static class CountdownTimeHandler extends Handler {

        public static final int MIN_COUNT = 0;
        //创建MainActivity弱引用
        final WeakReference<RegisterActivity> mWeakReference;

        public CountdownTimeHandler(RegisterActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }
        @Override
        public  void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获取对MainActivity的弱引用
            RegisterActivity activity = mWeakReference.get();
            switch (msg.what) {
                case COUNTDOWN_TIME_CODE:
                    int value = msg.arg1;
                    activity.verficationCodetime.setText(String.valueOf(value--));
                    //循环发送消息的控制
                    if (value >= MIN_COUNT) {
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_CODE;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    }else {
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_RESTART;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    }
                    break;
                case COUNTDOWN_TIME_RESTART:
                    activity.verficationCodetime.setText("重新发送");
                    activity.verficationCodetime.setEnabled(true);
                    break;
            }
        }
    }
    public static class CountdownTimeHandler1 extends Handler {

        public static final int MIN_COUNT = 0;
        //创建MainActivity弱引用
        final WeakReference<RegisterActivity> mWeakReference;

        public CountdownTimeHandler1(RegisterActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }
        @Override
        public  void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获取对MainActivity的弱引用
            RegisterActivity activity = mWeakReference.get();
            switch (msg.what) {
                case COUNTDOWN_TIME_CODE:
                    int value = msg.arg1;
                    activity.verficationEmailtime.setText(String.valueOf(value--));
                    //循环发送消息的控制
                    if (value >= MIN_COUNT) {
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_CODE;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    }else {
                        Message message = Message.obtain();
                        message.what = COUNTDOWN_TIME_RESTART;
                        message.arg1 = value;
                        sendMessageDelayed(message, DELAY_MILLIS);
                    }
                    break;
                case COUNTDOWN_TIME_RESTART:
                    activity.verficationEmailtime.setText("重新发送");
                    activity.verficationEmailtime.setEnabled(true);
                    break;
            }
        }
    }
    private void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    public void onRequestPermissionResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    openAlbum();
                else
                    Toast.makeText(RegisterActivity.this,"你没有权限",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:  // 请求码
                if (resultCode==RESULT_OK)
                    if (Build.VERSION.SDK_INT>=19)
                        handleImageOmKitKat(data);
                    else
                        handleImageBeforeKitKat(data);
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOmKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果document类型是U日，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是普通类型 用普通方法处理
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            //如果file类型位uri直街获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri, String selection){
        String path = null;
        //通过Uri和selection来获取真实图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if (imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            bytes=Bitmap2Bytes(bitmap);
            headRegister.setImageBitmap(bitmap);
        }else {
            Toast.makeText(RegisterActivity.this,"fail to get image",Toast.LENGTH_SHORT).show();
        }
    }
    public byte[] Bitmap2Bytes(Bitmap bm) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                return baos.toByteArray();
           }
}
