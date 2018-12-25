package com.lzs.sys.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	public static Map<String, Object> Bean2Map(Object obj) throws Exception {
		if(obj == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Class<?> cls = obj.getClass();
		Field[] beanFields = cls.getDeclaredFields();
		for (Field f : beanFields) {
			String filedName = f.getName();
			if(Modifier.isStatic(f.getModifiers())){ // if the field is static continue
				continue;
			}
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedName, cls);
			Object result = propertyDescriptor.getReadMethod().invoke(obj);
			
			map.put(filedName, result);

		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T MapToBean(Map<?, ?> map, Class<T> type)
			throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {

				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, value);
			}
		}
		return (T) obj;
	}
	public static Map<String, Object> FieldsToMap( Class<?> type) throws Exception{
		if(type == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Object obj = type.newInstance();
		Field[] beanFields = type.getDeclaredFields();
		for (Field f : beanFields) {
			String filedName = f.getName();
			
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(filedName, type);
			Object result = propertyDescriptor.getReadMethod().invoke(obj);
			
			map.put(filedName, result);

		}
		return map;
	}
	
	public static String reflectToString(Object obj) {  
        if (obj == null)  
            return "";  
        String returnValue = "";
        String[] classTypes={"class java.lang.Integer","class java.lang.String","class java.lang.Boolean","class java.lang.Character","class java.lang.Float","class java.lang.Double","class java.lang.Long","class java.lang.Short","class java.lang.Byte","class java.util.Date"};  
        boolean isNormalType = false; 
        for (String string : classTypes) {
			if(string.equalsIgnoreCase(obj.getClass().toString())){
				returnValue += obj.toString() + ",";
				isNormalType = true;
				break;
			}
		}
        if(!isNormalType){
        	 Field[] fields = obj.getClass().getDeclaredFields();  
             String[] types1={"int","java.lang.String","boolean","char","float","double","long","short","byte","java.util.Date"};  
             String[] types2={"java.lang.Integer","java.lang.String","java.lang.Boolean","java.lang.Character","java.lang.Float","java.lang.Double","java.lang.Long","java.lang.Short","java.lang.Byte","java.util.Date"};  
             for (int j = 0; j < fields.length; j++) {  
                 fields[j].setAccessible(true);  
                 for(int i=0;i<types1.length;i++){  
                     if(fields[j].getType().getName()  
                             .equalsIgnoreCase(types1[i])|| fields[j].getType().getName().equalsIgnoreCase(types2[i])){  
                         try {  
                             String keyValue = fields[j].getName() +":"+fields[j].get(obj);
                             returnValue += keyValue + ", \n ";
                         } catch (Exception e) {  
                             e.printStackTrace();  
                         }   
                     }  
                 }  
             }
        }
        return returnValue;
    }  
	
	public static HashMap<String,Object> changeMap2HashMap(Map<String,Object> map){
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		for(String key : map.keySet()){
			returnMap.put(key, map.get(key));
		}
		return returnMap;
	}

}
