package com.lzs.sys.config.shiro;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * shiro配置
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
        //s拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // s设置静态资源文件可访问
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon"); //s匿名访问静态资源
        filterChainDefinitionMap.put("/img/**", "anon"); //s匿名访问静态资源
        filterChainDefinitionMap.put("/js/**", "anon"); //s匿名访问静态资源
        filterChainDefinitionMap.put("/plugs/**", "anon"); //s匿名访问静态资源
        
        //s匿名访问登录
        filterChainDefinitionMap.put("/login", "anon"); 
        
        filterChainDefinitionMap.put("/getVerifyCode", "anon"); 
        
        
        //s配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- s过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "authc");
        
        // s如果不设置默认会自动寻找Web工程根目录下的"/login"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // s登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");

        //s未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/noPrivilege");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //s身份认证realm
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    // s开启shiro aop注解支持. 
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
        return authorizationAttributeSourceAdvisor;  
    }
    
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
    	HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //s加密方式
        credentialsMatcher.setHashAlgorithmName("md5");
        //s加密迭代次数
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }
    
   
}

