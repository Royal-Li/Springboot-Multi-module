/**
 * @date 4:44:00 PM
 * BeanCopyNotNull.java
 * Administrator
 * TODO
 */
package com.lzs.sys.util;
import java.lang.reflect.Field;
import java.util.Arrays;
/**
 * @class BeanCopyNotNull	
 * @author Warren
 * @description 相同对象之间复制字段
 * @date Nov 15, 2018 
 */
public class BeanCopyNotNull {
	
	/**
	 * @author Warren
	 * @param bean1 
	 * @param bean2 
	 * @param void
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @description 把bean1的基本类型、包装类、String类型的非空成员变量值赋给 bean2
	 * @date Nov 15, 2018
	 */
	public static void copyNotNullAndPrimitiveFiled(Object bean1,Object bean2) throws IllegalArgumentException, IllegalAccessException {
		String[] types = {"java.lang.Integer",
				"java.lang.Double",
			    "java.lang.Float",
			    "java.lang.Long",
			    "java.lang.Short",
			    "java.lang.Byte",
			    "java.lang.Boolean",
			    "java.lang.Character",
			    "java.lang.String",
			    "int","double","long","short","byte","boolean","char","float"};
		
		Field[] fields = bean1.getClass().getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			String currType =field.getType().getCanonicalName();
			/*for(String type : types) {
				if(currType.equals(type) ) {
					Object obj = field.get(bean1);
					if(obj!=null) {
						field.set(bean2, obj);
					}
				}
			}*/
			
			if(Arrays.asList(types).contains(currType)) {
				Object obj = field.get(bean1);
				if(obj!=null) {
					field.set(bean2, obj);
				}
			}
		}

	}
}
