package com.example.liangqg.qq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.bean.User;

import java.util.List;

public class FriendsAdapter extends ArrayAdapter {
    private List<User> friendsHandels ;
    private Context mContext ;
    private int resourceId ;
    public FriendsAdapter(Context context, int resource,List<User> data) {
        super(context, resource,data);
        this.mContext=context;
        this.friendsHandels=data;
        this.resourceId=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User friendsHandel=(User)getItem(position);
        View view;
        view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView personname=view.findViewById(R.id.personname);
        TextView autograph=view.findViewById(R.id.autograph);
        ImageView iv_image=view.findViewById(R.id.friend_head);
        personname.setText(friendsHandel.getName());
        iv_image.setImageResource(friendsHandel.getImageId());
        autograph.setText(friendsHandel.getAutograph());
        return view;
    }
}
