package com.example.liangqg.qq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.bean.MessageHandel;

import java.util.List;

public class MessagesAdapter extends ArrayAdapter {
    private List<MessageHandel> messageHandels ;
    private Context mContext ;
    private int resourceId ;
    public MessagesAdapter(Context context, int resource, List<MessageHandel> data) {
        super(context, resource,data);
        this.mContext=context;
        this.messageHandels=data;
        this.resourceId=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageHandel messageHandel=(MessageHandel)getItem(position);
        View view;
        view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView tv_num=view.findViewById(R.id.tv_num);
        TextView tv_message=view.findViewById(R.id.tv_message);
        TextView tv_name=view.findViewById(R.id.tv_name);
        TextView tv_time=view.findViewById(R.id.tv_time);
        ImageView iv_image=view.findViewById(R.id.iv_head);
        int j=Integer.parseInt(messageHandel.getNum());
        if(j>99)
           tv_num.setText("99+");
        else if(j>0&&j<=99)
            tv_num.setText(messageHandel.getNum());
        else
            tv_num.setVisibility(View.INVISIBLE);
        tv_message.setText(messageHandel.getMessage());
        iv_image.setImageResource(messageHandel.getHead());
        tv_name.setText(messageHandel.getName());
        tv_time.setText(messageHandel.getTime());
        return view;
    }
}
