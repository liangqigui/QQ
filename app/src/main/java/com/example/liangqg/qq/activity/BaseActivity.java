package com.example.liangqg.qq.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.liangqg.qq.util.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        ActivityCollector.addActivity(this);
    }
    public void onDestroy(BaseActivity activity){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
