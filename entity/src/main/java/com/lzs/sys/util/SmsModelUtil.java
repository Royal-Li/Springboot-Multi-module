package com.lzs.sys.util;

public class SmsModelUtil {


    //注册验证码提醒
    public static String SMS_YZM(String yzm){
        return String.format("productE提醒您：您当前注册验证码为：%s",yzm);
    }
    //获取验证码
    public static String getYZM(){
        int intFlag  = (int)(Math.random() * 1000000);
        String flag = String.valueOf(intFlag);
        if (flag.length() == 6 && flag.substring(0, 1).equals("9"))
        {
            System.out.println(intFlag);
        }
        else
        {
            intFlag = intFlag + 100000;
            System.out.println(intFlag);
        }
        return intFlag+"";
    }
  

}
