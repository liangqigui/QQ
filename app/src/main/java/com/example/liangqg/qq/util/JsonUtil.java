package com.example.liangqg.qq.util;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static String readJsonFile(Context context) {
        StringBuilder stringBuilder =null;
        try {
            InputStream is = context.getClass().getClassLoader().
                    getResourceAsStream("assets/" + "city.json");
            InputStreamReader streamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public static Map<String, Map<String,List<String>>> getMap(Context context){
        Map<String, Map<String,List<String>>> province=new HashMap<>();
        String jsonStr=readJsonFile(context);
        JSONArray jsonArray = JSONObject.parseArray(jsonStr);

        for(int i=0;i<jsonArray.size();i++){
            Map<String,List<String>> city=new HashMap<>();
            JSONObject provincejson= (JSONObject) jsonArray.get(i);
            String provincename=provincejson.getString("name");
            JSONArray cityArray=JSONObject.parseArray(provincejson.getString("city"));
            for(int j=0;j<cityArray.size();j++){
                List<String> list = new ArrayList<>();
                JSONObject cityjson= (JSONObject) cityArray.get(j);
                String cityname=cityjson.getString("name");
                String areaArray= cityjson.getString("area");
               String str=areaArray.replace("\"","").replace("[","")
                       .replace("]","");
               String[] array=str.split(",");
               for (int z=0;z<array.length;z++){
                   list.add(array[z]);
               }
               city.put(cityname,list);
            }
            province.put(provincename,city);
        }
        return province;
    }
}
