package com.example.liangqg.qq.util;

import com.example.liangqg.qq.bean.User;
import com.google.gson.Gson;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserToServer {
    HttpClient httpClient = new DefaultHttpClient();
    List<BasicNameValuePair> params = new ArrayList<>();
        //服务器返回的结果
        String result = "";

        /**
         * 使用Post方式向服务器发送请求并返回响应
         * @param account 传递给服务器的username
         * @param password 传递给服务器的password
         * @return
         */
        public String login(String account, String password,String url) throws IOException {
            User person=new User();
            HttpPost httpPost = new HttpPost(url);
            person.setName("南宫鹰");
            person.setIP("127.0.0.1");
            person.setAccount(account);
            person.setPort("8888");
            person.setPassword(password);
            Gson gson = new Gson();
            String user=gson.toJson(person);
            params.add(new BasicNameValuePair("user", user));
            //将参数包装如HttpEntity中并放入HttpPost的请求体中
            UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(httpEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            //如果响应成功
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //得到信息体
                HttpEntity entity = httpResponse.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String readLine = null;
                while((readLine = br.readLine()) != null){
                    result += readLine;
                }
                inputStream.close();
                return result;
            }
            //响应失败
            else{
                return "Error";
            }
        }
        public  String sendPhoneMessage(String phone,String url) throws IOException {
            HttpPost httpPost = new HttpPost(url);
            params.add(new BasicNameValuePair("phone", phone));
            //将参数包装如HttpEntity中并放入HttpPost的请求体中
            UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, "GBK");
            httpPost.setEntity(httpEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            //如果响应成功
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //得到信息体
                HttpEntity entity = httpResponse.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String readLine = null;
                while((readLine = br.readLine()) != null){
                    result += readLine;
                }
                inputStream.close();
                return result;
            }
            //响应失败
            else{
                return "Error";
            }
        }
    public  String checkPhoneCode(String code,String phone,String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("phone", phone));
        //将参数包装如HttpEntity中并放入HttpPost的请求体中
        UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        //如果响应成功
        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //得到信息体
            HttpEntity entity = httpResponse.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            while((readLine = br.readLine()) != null){
                result += readLine;
            }
            inputStream.close();
            return result;
        }
        //响应失败
        else{
            return "Error";
        }
    }
    public  String sendEmail(String email,String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        params.add(new BasicNameValuePair("email", email));
        //将参数包装如HttpEntity中并放入HttpPost的请求体中
        UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        //如果响应成功
        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //得到信息体
            HttpEntity entity = httpResponse.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            while((readLine = br.readLine()) != null){
                result += readLine;
            }
            inputStream.close();
            return result;
        }
        //响应失败
        else{
            return "Error";
        }
    }
    public  String checkEmailCode(String code,String email,String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("email", email));
        //将参数包装如HttpEntity中并放入HttpPost的请求体中
        UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        //如果响应成功
        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //得到信息体
            HttpEntity entity = httpResponse.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            while((readLine = br.readLine()) != null){
                result += readLine;
            }
            inputStream.close();
            return result;
        }
        //响应失败
        else{
            return "Error";
        }
    }
    public String addUser(User user,String url) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        Gson gson = new Gson();
        String userJson=gson.toJson(user);
        params.add(new BasicNameValuePair("user", userJson));
        //将参数包装如HttpEntity中并放入HttpPost的请求体中
        UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        //如果响应成功
        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //得到信息体
            HttpEntity entity = httpResponse.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            while((readLine = br.readLine()) != null){
                result += readLine;
            }
            inputStream.close();
            return result;
        }
        //响应失败
        else{
            return "Error";
        }
    }
}