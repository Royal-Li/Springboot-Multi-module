//package com.lzs.sys.util;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class TokenUtil {
//
//
//	/**
//	 * 加密
//	 * @author Arwen Liu
//	 * @date 2018-10-29
//	 * @param id 用户id
//	 * @param username 用户名称
//	 * @param password 用户密码
//	 * @param formatDate 过期日期
//	 * @return 加密的token
//	 */
//	public static String createToken(String id, String username, String password, String formatDate) {
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//		long nowMillis = Calendar.getInstance().getTimeInMillis();
//		Date now = new Date(nowMillis);
//
//		// 加密秘钥
//		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("123456789");
//		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//		JwtBuilder builder = Jwts.builder()
//				.setIssuedAt(now)
//				.setId(id)
//				.setIssuer(username)
//				.setSubject(password)
//				.signWith(signatureAlgorithm,signingKey);
//
//
//		// 设置过期日期
//		Date expirationDate = null;
//		if (null != formatDate) {
//			try {
//				expirationDate = DateUtil.stringToDate(formatDate);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if(null == expirationDate || expirationDate.before(new Date())){
//				expirationDate = getDefultExpirationDate();
//			}
//		} else {
//			expirationDate = getDefultExpirationDate();
//		}
//		builder.setExpiration(expirationDate);
//		
//		return builder.compact();
//	}
//	
//	/**
//	 * 获取默认的过期时间，默认是七天以后
//	 * @author Arwen Liu
//	 * @date 2018-10-29
//	 * @return Date
//	 */
//	private static Date getDefultExpirationDate(){
//		long timeInMillis = Calendar.getInstance().getTimeInMillis();
//		Long time = 1 * 1000 * 60 * 60 * 24 * 7l;
//		time += timeInMillis;
//		return new Date(time);
//	}
//
//	/**
//	 * 解密
//	 * @author Arwen Liu
//	 * @date 2018-10-29
//	 * @param token
//	 * @return Claims
//	 */
//	public static Claims parseToken(String token) {
//		Claims claims = Jwts.parser()
//				// 解密密钥，需要和 加密秘钥一致
//				.setSigningKey(DatatypeConverter.parseBase64Binary("123456789"))
//				.parseClaimsJws(token).getBody();
//
//		return claims;
//	}
//
//	public static void main(String[] args) {
//		String secretToken = createToken("1001", "张三", "123456", "2018-12-28");
//		System.out.println("加密后---->" + secretToken);
//		// 解密
//		Claims claims = parseToken(secretToken);
//		System.out.println("解密后---->");
//		System.out.println("id: " + claims.getId());
//		System.out.println("username: " + claims.getIssuer());
//		System.out.println("password: " + claims.getSubject());
//		
//		System.out.println("expiration: " + DateUtil.dateToString(claims.getExpiration(), "yyyy-MM-dd"));
//	/*	long timeInMillis = Calendar.getInstance().getTimeInMillis();
//		System.out.println(timeInMillis);*/
//
//	}
//
//}
