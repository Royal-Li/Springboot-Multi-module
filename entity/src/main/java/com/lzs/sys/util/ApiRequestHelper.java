///** 
// * ---------------------------------------------------------------------------------------------------------------
// * @Copyright	Quadrangle Systems Inc.
// * @Title		ApiRequestHelper.java   
// * @author 		Peak Zhang
// * @date 		2014-07-14
// * @Description: 
// *				Define useful functions for requesting baibay api services
// * ---------------------------------------------------------------------------------------------------------------
// */ 
//package com.lzs.sys.util;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import com.qs.education.bean.api.RequestHeaderNames;
//import com.qs.education.bean.commons.CommonDate;
//import com.qs.education.bean.http.HttpRequestCaller;
//
//public class ApiRequestHelper {
//	
//	/*private static String encryptedAuthenticationCode;
//	static{
//		String hexAuthenticationCode = SystemProperty.getValue("api.authentication.code");
//		String authenticationCode = new String(AES.hexToByte(AES.decrypt(AES.hexToByte(hexAuthenticationCode), "BaiBay")));
//		encryptedAuthenticationCode = AES.encrypt(authenticationCode, authenticationCode);
//	}*/
//	
//	/**
//	 * Add common headers to http request caller for API call, including application-token, request-time, authentication-code
//	 * @author		Peak Zhang
//	 * @param 		caller
//	 */
//	public static void addCommonHeaders(HttpRequestCaller caller){
//		if(caller != null){
//			caller.setHeaders(generateCommonHeaders());
//		}
//	}
//	public static void addAmazonHeaders(HttpRequestCaller caller, int headerType){
//		if(caller != null){
//			if(headerType == 1){
//				caller.setHeaders(generateAmazonHeaders_chrome());
//			}else if(headerType == 2){
//				caller.setHeaders(generateAmazonHeaders_ie11());
//			}else{
//				caller.setHeaders(generateAmazonHeaders_fireFox());
//			}
//		}
//	}
//	/**
//	 * Generate common headers for API call, including application-token, request-time, authentication-code
//	 * @author		Peak Zhang
//	 * @return		Map<String, String>
//	 */
//	public static Map<String, String> generateCommonHeaders(){
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put(RequestHeaderNames.TOKEN, UUID.randomUUID().toString());
//		headers.put(RequestHeaderNames.REQUEST_TIME, CommonDate.getInstance(CommonDate.TIME_STANMP).toString());
//	//	headers.put(RequestHeaderNames.AUTHENTICATION, encryptedAuthenticationCode);
//		headers.put("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
//		headers.put("connection", "keep-alive");
//		headers.put("Content-Type", "application/json;charset=utf-8");
//		return headers;
//	}
//	public static Map<String, String> generateAmazonHeaders_fireFox(){
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		headers.put("Accept-Encoding","gzip, deflate");
//		headers.put("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//		headers.put("Connection","keep-alive");
//		headers.put("Cookie",RequestHeaderNames.REQUEST_TIME+"="+CommonDate.getInstance(CommonDate.TIME_STANMP).toString());
//				//"x-wl-uid=1A4bHpyj87DoLGnvUpnOg+dLeJYuLRC4LnPqaaaihgxPq7exxzu6gfQmRoYe6a6G7Zo0spYBBdJw=; session-id-time=2082787201l; session-id=186-1328628-8005246; csm-hit=0PPXB8JARHVHP6EF3CKP+s-0PPXB8JARHVHP6EF3CKP|1421818194738; ubid-main=191-9857206-2080033; session-token=Bpt8+J5u6PHDcHLudBblVh+7zv8DFNGPz36q3iUb+lX57YEyQIoYrxzXkEv8dwwe4OINARwa4wQ3UBeBgpefoQGY33JhOJJgx1RS5xuho45Aq2YN9YQpb9xw4H22MdpsLFfsZeGUolAiAoxI6xrQNSPgMzEV+l5LP2r614L8ksPFb3cCJ1YzE7wK9kX8lkEXihJPQ5IHF4azv4rAX3D3Wn8XZlFiTGyabTEGsyg5awN0u6bcIyN5MGNb1YACKXAP");
//		headers.put("Host","www.amazon.com");
//		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
//		return headers;
//	}
//	public static Map<String, String> generateAmazonHeaders_chrome(){
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//		headers.put("Accept-Encoding","gzip,deflate,sdch");
//		headers.put("Accept-Language","zh-CN,zh;q=0.8");
//		headers.put("Connection","keep-alive");
//		//headers.put("Cookie","x-wl-uid=1mvT2EVoYxkkIXvhK5KvyLfifjYeTNLVHgBwnDwcdPlm9AVhOfONqZlW64mQ61+QHLb48Qk0/H7o=; session-token=YV9R2nFEX0qsmdi4QobC+pxD98gLOZSuV03EdTRzTJJ6C9q4jDfVD7arTiYgxRWctZqQOGaBlijpT+s9nlsBvEFHYf4Y6uxrNmcaWRa/8YCS7BLafkMNASCN8yqZiCnyPYHr+f+ybNZvdDqkX20geIWP5Mz1r2Zg1CvhLUT2NHZwW31aC4mBLshosulSiV0f2TSnTBL8IzhiDsZMpiTLUqqDfMfQvptilpLJ6M1Ur8Ub+Ssk+XZk70O0ZquxHt8V; skin=noskin; csm-hit=1270NA9PZZE59KAZRH6Z+s-1270NA9PZZE59KAZRH6Z|1422525424666; ubid-main=179-1641152-1509805; session-id-time=2082787201l; session-id=178-4851444-0622969");
//		headers.put("Host","www.amazon.com");
//		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");
//		return headers;
//	}
//	public static Map<String, String> generateAmazonHeaders_ie11(){
//		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Accept","text/html, application/xhtml+xml, */*");
//		headers.put("Accept-Encoding","gzip, deflate");
//		headers.put("Accept-Language","zh-CN");
//		headers.put("Connection","Keep-Alive");
//		//headers.put("Cookie","x-wl-uid=11Q334wZIOLilb75bo3loHyuTzxvZnn1is9fwFEOs8mxaiIN2O/ZxypdAHS5jawwbV9v+wLfbyUk=; session-id-time=2082787201l; session-id=177-3399108-6520404; ubid-main=182-8957569-9811201; session-token=sHKSin0W8PGHkvZZuq4ogqx4+aJa2ahHJwulJzBg9DmSp8sIiRw2vC98iyv6wsKo8knP3mtLqrMiSRfut6jzGoJbfVowmJVcK420yEovPSd26gl2dbHIBPv7SKusgUgXlJBztFNNEOb0YWolvRNF/h8KQUdDcQjlc0LPt8KYyFW+yp/a8qXI39rDIc9WkoAHOICTys7LMstUN8LEMsGBK7zVOF+MVlB9mnro1Ni0bOTLAVHJ5Bvv9j9tHmOizwc6; csm-hit=0RHR9T78RSEHW6X48EQC+s-0RHR9T78RSEHW6X48EQC|1422525910153");
//		headers.put("Host","www.amazon.com");
//		headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
//		return headers;
//	}
//}
