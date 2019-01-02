package com.lzs.sys.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lzs.sys.config.filter.KickoutSessionControlFilter;

/**
 * shiro配置
 * 
 * @class ShiroConfig
 * @author Jason
 * @description
 * @date Dec 25, 2018 5:15:14 PM
 */
@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 自定义拦截器限制并发人数,参考博客
		LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
		// 限制同一帐号同时在线的个数
		filtersMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);

		// s拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// s设置静态资源文件可访问
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon"); // s匿名访问静态资源
		filterChainDefinitionMap.put("/img/**", "anon"); // s匿名访问静态资源
		filterChainDefinitionMap.put("/js/**", "anon"); // s匿名访问静态资源
		filterChainDefinitionMap.put("/plugs/**", "anon"); // s匿名访问静态资源

		/*
		 * //s匿名访问登录 filterChainDefinitionMap.put("/login", "anon");
		 */
		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/login", "kickout,anon");
		filterChainDefinitionMap.put("/getVerifyCode", "anon");

		// s配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");

//        s过滤链定义，从上向下顺序执行，一般将/**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
//        authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/*", "authc");
		// filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/*.*", "authc");
		// 其他资源都需要认证 authc表示需要认证才能进行访问 user表示配置记住我或认证通过可以访问的地址
		// filterChainDefinitionMap.put("/**", "kickout,user"); 表示 访问/**下的资源 首先要通过
		// kickout 后面的filter，然后再通过user后面对应的filter才可以访问。
		filterChainDefinitionMap.put("/**", "kickout,user");

		// s如果不设置默认会自动寻找Web工程根目录下的"/login"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// s登录成功后要跳转的链接
		// shiroFilterFactoryBean.setSuccessUrl("/index");

		// s未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/noPrivilege");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	// s身份认证realm
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// s加密方式
		credentialsMatcher.setHashAlgorithmName("md5");
		// s加密迭代次数
		credentialsMatcher.setHashIterations(1);
		return credentialsMatcher;
	}
	
	/**
	 * @Description 开启缓存 shiro cache实现
	 * @author Jason
	 * @date Dec 29, 2018
	 * @return
	 */
	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return ehCacheManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public MyShiroRealm shiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCachingEnabled(true);
		// 启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
		myShiroRealm.setAuthenticationCachingEnabled(true);
		// 缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
		myShiroRealm.setAuthenticationCacheName("authenticationCache");
		// 启用授权缓存，即缓存AuthorizationInfo信息，默认false
		myShiroRealm.setAuthorizationCachingEnabled(true);
		// 缓存AuthorizationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
		myShiroRealm.setAuthorizationCacheName("authorizationCache");
		return myShiroRealm;
	}
	
	/**
	 * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
	 * MemorySessionDAO 直接在内存中进行会话维护
	 * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
	 * @return
	 */
	@Bean
	public SessionDAO sessionDAO() {
	    EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
	    //使用ehCacheManager
	    enterpriseCacheSessionDAO.setCacheManager(ehCacheManager());
	    //设置session缓存的名字 默认为 shiro-activeSessionCache
	    enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
	   /* //sessionId生成器
	    enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());*/
	    return enterpriseCacheSessionDAO;
	}
	
	/**
	 * @Description 自定义session 管理 a. AbstractSessionDAO提供了SessionDAO的基础实现，如生成会话ID等；
	 *              b. CachingSessionDAO提供了对开发者透明的会话缓存的功能，只需要设置相应的CacheManager即可； c.
	 *              MemorySessionDAO直接在内存中进行会话维护； d.
	 *              EnterpriseCacheSessionDAO提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
	 * @author Jason
	 * @date Dec 29, 2018
	 * @return
	 */
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
		sessionManager.setCacheManager(ehCacheManager());
		return sessionManager;
	}

	/**
	 * @Description s配置核心安全事务管理器
	 * @author Jason
	 * @date Jan 2, 2019
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		// s自定义cacheManager
		securityManager.setCacheManager(ehCacheManager());
		// s自定义sessionManager
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	// s开启shiro aop注解支持.
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 并发登录控制
	 * 
	 * @return
	 */
	@Bean
	public KickoutSessionControlFilter kickoutSessionControlFilter() {
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		// 用于根据会话ID，获取会话进行踢出操作的；
		kickoutSessionControlFilter.setSessionManager(sessionManager());
		// 使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
		kickoutSessionControlFilter.setCacheManager(ehCacheManager());
		// 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
		kickoutSessionControlFilter.setKickoutAfter(false);
		// 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
		kickoutSessionControlFilter.setMaxSession(1);
		// 被踢出后重定向到的地址；
		kickoutSessionControlFilter.setKickoutUrl("/login?kickout=1");
		return kickoutSessionControlFilter;
	}

}
