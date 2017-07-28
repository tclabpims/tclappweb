package com.tcl.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;

import java.util.List;

/**
 * Created by wang on 2017-07-27.
 */
public class JMessageUtils {
    private static String masterSecret = "45ec5ccd2999902e7d81784c";
    private static String appKey = "624a57bc4a6b9c73a76a5f89";
    //单个注册
    public static String registerUser(String userName) {
        JMessageClient client = new JMessageClient(appKey, masterSecret);
        try {
            RegisterInfo.Builder builder=RegisterInfo.newBuilder();
            builder.setPassword("tcl123456");
            builder.setUsername(userName);
            RegisterInfo[] users={builder.build()};
            String result=client.registerUsers(users);
            System.out.println(result);
            return result;
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return "";
    }
    //批量注册
    public static String registerUsers(List<String> userNames) {
        JMessageClient client = new JMessageClient(appKey, masterSecret);
        try {
            if(userNames!=null&&userNames.size()>0){
                RegisterInfo[] users=new RegisterInfo[userNames.size()];
                for(int i=0;i<userNames.size();i++){
                    RegisterInfo.Builder builder=RegisterInfo.newBuilder();
                    builder.setPassword("tcl123456");
                    builder.setUsername(userNames.get(i));
                    users[i]=builder.build();
                }
                String result=client.registerUsers(users);
                System.out.println(result);
                return result;
            }

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return "";
    }
    /*
    public static void main(String[] args) {
        registerUsers();
    }*/
}
