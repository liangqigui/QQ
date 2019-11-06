package com.example.liangqg.qq.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.adapter.FriendsAdapter;
import com.example.liangqg.qq.adapter.MessagesAdapter;
import com.example.liangqg.qq.bean.MessageHandel;
import com.example.liangqg.qq.bean.User;
import com.example.liangqg.qq.layout.AddTitleLayout;
import com.example.liangqg.qq.layout.TitleLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

private String[] names=null;
private String[] times=null;
private String[] messages=null;
private String[] nums=null;
private String[] friends_names=null;
private String[] autographs=null;

private AddTitleLayout main_add_title;
private RelativeLayout main_RL;
private LinearLayout main_add;
private TitleLayout main_titlelayput;
private ListView listview;
private ListView friends_list;
private Button main_message,main_contacts,main_dynamic;
private TextView title_control;
private ImageView main_Head;
private List<MessageHandel> messageHandelList=new ArrayList<>();
private List<User> userList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        main_add=(LinearLayout)findViewById(R.id.main_add);
        main_RL=(RelativeLayout)findViewById(R.id.main_RL);
        listview=(ListView)findViewById(R.id.listview);
        friends_list=(ListView)findViewById(R.id.friends_list);
        main_message=(Button)findViewById(R.id.main_message);
        main_contacts=(Button)findViewById(R.id.main_contacts);
        main_dynamic=(Button)findViewById(R.id.main_dynamic);
        title_control=(TextView)findViewById(R.id.title_control);
        main_Head=(ImageView)findViewById(R.id.main_Head);
        main_titlelayput=(TitleLayout)findViewById(R.id.main_titlelayput);
        main_add_title=(AddTitleLayout)findViewById(R.id.main_add_title);
        main_add_title.setRelativeLayout(main_RL,main_add);
        main_titlelayput.setRelativeLayout(main_RL,main_add);
        initData();
        main_dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FriendDynamicActivity.class);
                startActivity(intent);
            }
        });
        main_Head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PersonActivity.class);
                startActivity(intent);
            }
        });
        main_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_control.setText("联系人");
                main_message.setTextColor(Color.rgb(0,0,0));
                main_contacts.setTextColor(Color.rgb(9,161,245));
                listview.setVisibility(View.INVISIBLE);
                friends_list.setVisibility(View.VISIBLE);
            }
        });
        main_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_control.setText("消息");
                main_message.setTextColor(Color.rgb(9,161,245));
                main_contacts.setTextColor(Color.rgb(0,0,0));
                friends_list.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
            }
        });
        MessagesAdapter messagesAdapter = new MessagesAdapter ( MainActivity.this ,
                R.layout.news_list,messageHandelList ) ;
        listview.setAdapter(messagesAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,PersenMessageActivity.class);
                startActivity(intent);
            }
        });
        FriendsAdapter friendsAdapter=new FriendsAdapter(MainActivity.this,
                R.layout.friends_list, userList);
        friends_list.setAdapter(friendsAdapter);
        friends_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,PersenMessageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        names=getResources().getStringArray(R.array.names);
        times=getResources().getStringArray(R.array.times);
        messages=getResources().getStringArray(R.array.messages);
        nums=getResources().getStringArray(R.array.nums);
        friends_names=getResources().getStringArray(R.array.names);
        autographs=getResources().getStringArray(R.array.autographs);
        TypedArray images = getResources().obtainTypedArray(R.array.heads) ;
        for (int i=0;i<22;i++){
            MessageHandel messageHandel=new MessageHandel();
            User user =new User();

            messageHandel.setHead(images.getResourceId(i,0));
            messageHandel.setNum(nums[i]);
            messageHandel.setName(names[i]);
            messageHandel.setTime(times[i]);
            messageHandel.setMessage(messages[i]);
            messageHandelList.add(messageHandel);

            user.setImageId(images.getResourceId(i,0));
            user.setAutograph(autographs[0]);
            user.setName(friends_names[i]);

            userList.add(user);
        }
    }
}
