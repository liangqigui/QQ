package com.example.liangqg.qq.activity;

import android.os.Bundle;
import android.view.Window;

import com.example.liangqg.qq.R;

public class FriendDynamicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_friend);
    }
}
