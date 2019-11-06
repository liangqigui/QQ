package com.example.liangqg.qq.layout;

import android.content.Context;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.liangqg.qq.R;

import java.util.ArrayList;
import java.util.List;

public class TimeLayout extends LinearLayout {
    private Spinner time1,time2,time3;
    private ArrayAdapter<Integer> yearadapter,monthadapter,dayadapter;
    List<Integer> yearlist=new ArrayList<>();
    List<Integer> momthlist=new ArrayList<>();
    List<Integer> daylist = new ArrayList<>();
    public TimeLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
        LinearLayout linearLayout= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.time, this);
        init(linearLayout);
    }
    private void init(LinearLayout linearLayout){
        LinearLayout linearLayout1= (LinearLayout) linearLayout.getChildAt(0);
        LinearLayout linearLayout2=(LinearLayout)linearLayout1.getChildAt(0);
        time1=(Spinner)linearLayout2.getChildAt(0);
        time2=(Spinner)linearLayout2.getChildAt(1);
        time3=(Spinner)linearLayout1.getChildAt(1);
        final Integer[] days={31,28,31,30,31,30,31,31,30,31,30,31};
        Time t=new Time();
        t.setToNow();

        final int year = t.year;
        final int month = t.month+1;
        final int day = t.monthDay;

        int a=year;
        yearlist.clear();
        for (;a>=1900;a--)
            yearlist.add(a);
        yearadapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, yearlist);
        time1.setAdapter(yearadapter);
        time1.setSelection(0,true);

        Integer y=yearlist.get(0);
        momthlist.clear();
        if (y!=year)
            for(int i=1;i<=12;i++)
                momthlist.add(i);
        else
            for (int i=1;i<=month;i++)
                momthlist.add(i);
        monthadapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, momthlist);
        time2.setAdapter(monthadapter);
        time2.setSelection(0,true);

        Integer y1 = yearlist.get(0);
        Integer m1 = momthlist.get(0);

        if(y!=year){
            if(is_Leapyear(y)){
                days[1]=29;
                for (int i=1;i<=days[m1-1];i++)
                    daylist.add(i);
            }else {
                days[1]=28;
                for (int i=1;i<=days[m1-1];i++)
                    daylist.add(i);
            }
        }else {
            if (is_Leapyear(y)){
                days[1]=29;
                if(m1!=month){
                    for (int i=1;i<=days[m1-1];i++)
                        daylist.add(i);
                }else {
                    for (int i=1;i<=day;i++)
                        daylist.add(i);
                }
            }else {
                days[1]=28;
                if(m1!=month){
                    for (int i=1;i<=days[m1-1];i++)
                        daylist.add(i);
                }else {
                    for (int i=1;i<=day;i++)
                        daylist.add(i);
                }
            }
        }
        dayadapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, daylist);
        time3.setAdapter(dayadapter);
        time3.setSelection(0,true);

        time1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                momthlist.clear();
                Integer y=(Integer)time1.getSelectedItem();
                if (y!=year)
                    for(int i=1;i<=12;i++)
                        momthlist.add(i);
                else
                    for (int i=1;i<=month;i++)
                        momthlist.add(i);
                monthadapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, momthlist);
                time2.setAdapter(monthadapter);
                time2.setSelection(0,true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        time2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               daylist.clear();
                Integer y = (Integer) time1.getSelectedItem();
                Integer m = (Integer) time2.getSelectedItem();

                if(y!=year){
                    if(is_Leapyear(y)){
                        days[1]=29;
                        for (int i=1;i<=days[m-1];i++)
                            daylist.add(i);
                    }else {
                        days[1]=28;
                        for (int i=1;i<=days[m-1];i++)
                            daylist.add(i);
                    }
                }else {
                    if (is_Leapyear(y)){
                        days[1]=29;
                        if(m!=month){
                            for (int i=1;i<=days[m-1];i++)
                                daylist.add(i);
                        }else {
                            for (int i=1;i<=day;i++)
                                daylist.add(i);
                        }
                    }else {
                        days[1]=28;
                        if(m!=month){
                            for (int i=1;i<=days[m-1];i++)
                                daylist.add(i);
                        }else {
                            for (int i=1;i<=day;i++)
                                daylist.add(i);
                        }
                    }
                }

                dayadapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, daylist);
                time3.setAdapter(dayadapter);
                time3.setSelection(0,true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    protected boolean is_Leapyear(int year){
        if((year%4==0&&year%100!=0)||year%400==0)
            return  true;
        else
            return false;
    }
    public void initSelect(int year,int month,int day){
        if(yearlist.contains(year)){
            int i=yearlist.indexOf(year);
            time1.setSelection(i,true);
        }
        if(momthlist.contains(month)){
            int i=momthlist.indexOf(month);
            time2.setSelection(i,true);
        }
        if(daylist.contains(day)){
            int i=daylist.indexOf(day);
            time3.setSelection(i,true);
        }
    }
    public Integer[] getBirth(){
        Integer y = (Integer) time1.getSelectedItem();
        Integer m = (Integer) time2.getSelectedItem();
        Integer d = (Integer) time3.getSelectedItem();
        Integer[] birth={y,m,d};
        return birth;
    }
}
