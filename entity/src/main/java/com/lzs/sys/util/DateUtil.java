	package com.lzs.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{
	/**
	* 
	* @description   String 转 date
	* @param str
	* @return Date
	* @throws Exception
	*/
	public static Date stringToDate(String str) throws Exception{
		
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = format.parse(str);
		} catch (Exception e) {
			if(e instanceof ParseException){
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					date = format.parse(str);
				} catch (Exception e2) {
					if(e2 instanceof ParseException){
						try {
							SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
							date = format.parse(str);
						} catch (Exception e3) {
							if(e3 instanceof ParseException){
								try {
									SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
									date = format.parse(str);
								} catch (Exception e4) {
									e4.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		
		//System.out.println(date);
		return date;
	}
	
	
	/**
	 *  String 转 Date 
	 * @description  
	 * @param str 传入的String 日期
	 * @param dateFormat 传入的 String的format格式(如：yyyy-MM-dd;yyyy/MM/dd ... 等等)
	 * @return Date
	 * @throws Exception
	 */
	public static Date stringToDate(String str,String dateFormat) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date date= format.parse(str);
		return date;
	}
	 
	 
	/**
	* 
	* @description  date转String，转换后的String格式是：yyyy-MM-dd HH:mm:ss
	* @param date 传入的日期
	* @return String 转成String后的日期格式
	*/
	public static String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String strDate = format.format(date);
		System.out.println(strDate);
		return strDate;
	}
	/**
	 * Date 转 String
	 * @description  
	 * @param date 传入的date
	 * @param dataFormat 转换成String的格式 (如：yyyy-MM-dd;yyyy/MM/dd ... 等等)
	 * @return String
	 */
	public static String dateToString(Date date, String dataFormat){
		SimpleDateFormat format = new SimpleDateFormat(dataFormat); 
		String strDate = format.format(date);
		return strDate;
	}
	 
	 
	/**
	* 
	* @description  计算两个日期之间相差的天数
	* @param startDate
	* @param endDate
	* @return
	*/
	public static int daysBetweenTowDates(Date startDate,Date endDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timeStart = calendar.getTime().getTime();
		calendar.setTime(endDate);
		long timeEnd = calendar.getTime().getTime();
		int days = (int)(timeEnd / 1000 - timeStart / 1000)/3600/24;
		return Math.abs(days);
	}
	 
	 
	 
	/**
	* 
	* @description  计算两日期之间相差的年数
	* @param startDate
	* @param endDate
	* @return
	*/
	public static int yearsBetweenTowDates(Date startDate, Date endDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int startYear = calendar.get(Calendar.YEAR);
		calendar.setTime(endDate);
		int endYear = calendar.get(Calendar.YEAR);
		System.out.println(endYear - startYear);
		return endYear - startYear;
	}
	 
	/**
	 * 通过出生日期计算年龄
	 * @description  
	 * @param birthday  出生日期
	 * @param nowDate   传入的日期（）
	 * @return int 年龄值
	 */
	public static int calculateAge(Date birthday,Date nowDate) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(nowDate);
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		
		
		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		
		if(yearNow == yearBirth && monthNow == monthBirth && dayOfMonthNow == dayOfMonthBirth){ //birthday is today
			return 0;
		}else{
			Calendar birthdayCal = Calendar.getInstance();
			birthdayCal.setTime(birthday);
			if ( cal.before(birthdayCal) ) {
				throw new IllegalArgumentException(
						"The birthDay is before Now.It's unbelievable!");
			}
			int age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					age--;
				}
			}
			return age;
			
		}
		
	}
	public static void main(String[] args) throws Exception {
		String str_date = "2018-10-11";
		Date stringToDate = stringToDate(str_date);
		System.out.println(stringToDate);
		
		
	}
 
}
