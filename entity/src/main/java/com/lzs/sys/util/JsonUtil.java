/** 
 * ---------------------------------------------------------------------------------------------------------------
 * @Copyright	Quadrangle Systems Inc.
 * @Title		JsonUtil.java   
 * @author 		Peak Zhang
 * @date 		2014-07-11
 * @Description: 
 *				Define functions that convert json string to bean or convert bean to string
 * ---------------------------------------------------------------------------------------------------------------
 */ 
package com.lzs.sys.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String beanToJson(Object obj) {
		StringWriter writer = null;
		JsonGenerator gen = null;
		try{
			ObjectMapper mapper = new ObjectMapper();
			writer = new StringWriter();
			gen = new JsonFactory().createGenerator(writer);
			mapper.writeValue(gen, obj);
			String json = writer.toString();
			return json;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(gen != null){
				try {
					gen.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

    public static Object  jsonToBean(String json, Class<?> cls) {   
    	ObjectMapper mapper = new ObjectMapper();
        Object vo;
		try {
			vo = mapper.readValue(json, cls);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}   
        return vo;   
    }

    public static Object  jsonToBean(String json, TypeReference<?> type) {   
    	ObjectMapper mapper = new ObjectMapper();
        Object vo;
		try {
			vo = mapper.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}   
        return vo;   
    }
    
  

    /**
     * 获取 Class 中的 Field
     *
     * @param clazz     类字节码对象
     * @param filedName 字段名称
     * @return 字段字节码对象
     */
    public static Field getDeclaredFiled(Class<?> clazz, String filedName) {
        Field declaredField = null;
        while (clazz != null) {
            try {
                declaredField = clazz.getDeclaredField(filedName);
                if (declaredField != null || clazz.getSuperclass() == null) {
                    break;
                }
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return declaredField;
    }

    /**
     * 脱敏字符串
     *
     * @param value        原始字符串
     * @param prefixLength 前缀长度
     * @param suffixLength 后缀长度
     * @param asteriskNum  星号个数
     * @return 脱敏后的字符串
     */
    public static String desensitization(String value, int prefixLength, int suffixLength, int asteriskNum) {
        if (value != null && value.length() >= Math.max(prefixLength, suffixLength)) {
            StringBuilder builder = new StringBuilder();
            builder.append(value.substring(0, prefixLength));
            for (int i = 0; i < asteriskNum; i++) {
                builder.append("*");
            }
            builder.append(value.substring(value.length() - suffixLength, value.length()));
            return builder.toString();
        }
        return value;
    }
}
