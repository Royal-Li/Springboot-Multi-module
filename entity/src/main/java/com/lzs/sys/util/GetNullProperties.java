package com.lzs.sys.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class GetNullProperties {
	
	/**
	 * @Description 获取空属性
	 * @author Jason
	 * @date Nov 21, 2018
	 * @param src
	 * @return
	 */
	public static String[] getNullProperties(Object src) {
		// 1.获取Bean
		BeanWrapper srcBean = new BeanWrapperImpl(src);
		// 2.获取Bean的属性描述
		PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
		// 3.获取Bean的空属性
		Set<String> properties = new HashSet<>();
		for (PropertyDescriptor propertyDescriptor : pds) {
			String propertyName = propertyDescriptor.getName();
			Object propertyValue = srcBean.getPropertyValue(propertyName);
			if (StringUtils.isEmpty(propertyValue)) {
				srcBean.setPropertyValue(propertyName, null);
				properties.add(propertyName);
			}
		}
		return properties.toArray(new String[0]);
	}

}
