/**
 * @date 5:49:16 PM
 * SmsUtil.java
 * Administrator
 * TODO
 */
package com.lzs.sys.util;

/**
 * @class SmsUtil	
 * @author Warren
 * @description
 * @date Dec 11, 2018 
 */
public class SmsUtil {
	static String url="http://118.178.182.159:8088/sms.aspx";
    static String ID="296";
    static String account="3047";
    static String psw="123.com";

    public static boolean sendSms(String mobile,int sendType,String code){
    	 String str = "";
    	 if(sendType == 1) {
    		str = SmsClientSend.sendSms(url,ID,account,psw,mobile, SmsModelUtil.SMS_YZM(code));
    	 }
       
         return str.contains("<returnstatus>Success</returnstatus>");
    }
}
