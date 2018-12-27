package com.lzs.sys.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年7月22日
 * @Description 自定义json响应式结构
 * @version V1.0
 */
public class StatusResult implements Serializable {
	private static final long serialVersionUID = 5387303482092052390L;

	public static final String UPDATE_SUCCESS = "修改成功";
	public static final String UPDATE_FAIL = "修改失败";
	public static final String FIND_SUCCESS = "查询成功";
	public static final String FIND_FAIL = "查询失败";
	public static final String FIND_NONE = "不存在";
	public static final String FIND_EXIST = "已存在";
	public static final String ADD_SUCCESS = "添加成功";
	public static final String ADD_FAIL = "添加失败";
	public static final String DELETE_SUCCESS = "删除成功";
	public static final String DELETE_FAIL = "删除失败";
	public static final String DELETE_FAIL_FK = "删除失败，请先删除与此关联的对象";
	public static final String DELETE_FAIL_SUICIDE = "删除失败，不能删除自身";
	public static final String NO_AUTHORITY = "您无此功能权限";
	public static final String AVAILABLE = "该名称可用";
	public static final String UPLOAD_SUCCESS = "上传成功";
	public static final String UPLOAD_FAIL = "上传失败";

	public static final String LOGIN_INVALID = "登录失效";

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	private Integer status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public static StatusResult build(Integer status, String msg, Object data) {
		return new StatusResult(status, msg, data);
	}

	/**
	 * 返回data
	 * 
	 * @param data
	 * @return
	 */
	public static StatusResult ok(Object data) {
		return new StatusResult(data);
	}

	/**
	 * 不返回data
	 * 
	 * @param msg
	 * @return
	 */
	public static StatusResult ok(String msg) {
		return new StatusResult(200, msg, null);
	}

	public static StatusResult ok() {
		return new StatusResult(null);
	}

	public StatusResult() {

	}

	public static StatusResult build(Integer status, String msg) {
		return new StatusResult(status, msg, null);
	}

	public StatusResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public StatusResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	// public Boolean isOK() {
	// return this.status == 200;
	// }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果集转化为TaotaoResult对象
	 * 
	 * @param jsonData json数据
	 * @param clazz    TaotaoResult中的object类型
	 * @return
	 */
	public static StatusResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, StatusResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 没有object对象的转化
	 * 
	 * @param json
	 * @return
	 */
	public static StatusResult format(String json) {
		try {
			return MAPPER.readValue(json, StatusResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object是集合转化
	 * 
	 * @param jsonData json数据
	 * @param clazz    集合中的类型
	 * @return
	 */
	public static StatusResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 找不到
	 * 
	 * @param msg
	 * @return
	 */
	public static StatusResult none(String msg) {
		return new StatusResult(404, msg, null);
	}

	/**
	 * 已存在
	 * 
	 * @param msg
	 * @return
	 */
	public static StatusResult exist(String msg) {
		return new StatusResult(444, msg, null);
	}

	/**
	 * 运行异常
	 * 
	 * @param msg
	 * @return
	 */
	public static StatusResult error(String msg) {
		return new StatusResult(500, msg, null);
	}

	/**
	 * 失效
	 * 
	 * @param msg
	 * @return
	 */
	public static StatusResult loginInvalid() {
		return new StatusResult(400, LOGIN_INVALID, null);
	}

}
