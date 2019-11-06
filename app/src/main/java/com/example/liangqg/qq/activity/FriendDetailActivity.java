package com.example.liangqg.qq.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.layout.FriendDetailTitle;

public class FriendDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firend_detail);
    }
}
