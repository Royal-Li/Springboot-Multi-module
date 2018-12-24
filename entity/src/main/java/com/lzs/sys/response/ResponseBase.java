/** 
 * ---------------------------------------------------------------------------------------------------------------
 * @Copyright	Quadrangle Systems Inc.
 * @Title		ResponseBase.java   
 * @author 		Peak Zhang
 * @date 		2014-07-11
 * @Description: 
 *				Base bean for response from services
 * ---------------------------------------------------------------------------------------------------------------
 */ 
package com.lzs.sys.response;

import javax.servlet.http.HttpServletRequest;

/*import com.qs.education.bean.commons.CommonDate;*/


public abstract class ResponseBase {
	private String status;
	private String errorCode;
	private String message;
	private String responseTime;
	private String token;

	public ResponseBase(){
		/*responseTime = CommonDate.getInstance(CommonDate.TIME_STANMP).toString();*/
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getToken() {
		return token;
	}
	
	public String getToken(HttpServletRequest request){
		/*String token = request.getHeader(RequestHeaderNames.TOKEN);*/
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
