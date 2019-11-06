package com.example.liangqg.qq.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.liangqg.qq.R;
import com.example.liangqg.qq.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddressLayout extends LinearLayout {
    private Spinner s1,s2,s3;
    private ArrayAdapter<String> adapter,cityadapter;
    private List<String> provincelist=new ArrayList<>();
    private List<String> citylist=new ArrayList<>();
    private List<String> arealist=new ArrayList<>();
    private Map<String, List<String>> citymap;
    public AddressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
       LinearLayout linearLayout= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.addresss, this);
        LinearLayout linearLayout1= (LinearLayout) linearLayout.getChildAt(0);
        LinearLayout linearLayout2=(LinearLayout)linearLayout1.getChildAt(0);
        s1= (Spinner) linearLayout2.getChildAt(0);
        s2=(Spinner) linearLayout2.getChildAt(1);
        s3= (Spinner) linearLayout1.getChildAt(1);
        final Map<String, Map<String, List<String>>> data = JsonUtil.getMap(getContext());
        Set<String> provinceKeys = data.keySet();
        provincelist = new ArrayList<>();
        for (String key : provinceKeys)
            provincelist.add(key);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, provincelist);
        s1.setAdapter(adapter);
        s1.setSelection(0,true);
        String str= provincelist.get(0);
        citylist.clear();
        citymap=new HashMap<>();
        citymap=data.get(str);
        Set<String> cityKeys = citymap.keySet();
        for (String citykey : cityKeys)
            citylist.add(citykey);
        cityadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, citylist);
        s2.setAdapter(cityadapter);
        s2.setSelection(0,true);

        String str1= citylist.get(0);
        arealist.clear();
        arealist=citymap.get(str1);
        cityadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arealist);
        s3.setAdapter(cityadapter);
        s3.setSelection(0,true);


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str= (String) s1.getSelectedItem();
                citylist.clear();
                citymap=new HashMap<>();
                citymap=data.get(str);
                Set<String> cityKeys = citymap.keySet();
                for (String citykey : cityKeys)
                    citylist.add(citykey);
                cityadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, citylist);
                s2.setAdapter(cityadapter);
                s2.setSelection(0,true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str= (String) s2.getSelectedItem();
                arealist.clear();
                arealist=citymap.get(str);
                cityadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arealist);
                s3.setAdapter(cityadapter);
                s3.setSelection(0,true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void initSelect(String province,String city,String area){
        if(provincelist.contains(province)){
            int i=provincelist.indexOf(province);
            s1.setSelection(i,true);
        }
        if(citylist.contains(city)){
            int i=provincelist.indexOf(city);
            s2.setSelection(i,true);
        }
        if(arealist.contains(area)){
            int i=provincelist.indexOf(area);
            s3.setSelection(i,true);
        }
    }
    public String[] getAddress(){
        String province=(String) s1.getSelectedItem();
        String city=(String) s2.getSelectedItem();
        String area=(String) s3.getSelectedItem();
        String[] address={province,city,area};
        return address;
    }
}
