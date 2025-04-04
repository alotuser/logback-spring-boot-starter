package cn.lb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.lb.base.BaseUser;
import cn.lb.filter.UserFilter;

/**
 * 用户过滤器配置
 */
@ConditionalOnBean(BaseUser.class)
@Configuration
public class UserFilterConfig{

	/**
     * 用户过滤器
     * 
     * @param baseUser
     * @return FilterRegistrationBean<UserFilter> 
     */
	@Bean
	@ConditionalOnMissingBean
	FilterRegistrationBean<UserFilter>  filterRegistrationBean(@Autowired BaseUser baseUser) {
		FilterRegistrationBean<UserFilter>  filterRegistrationBean = new FilterRegistrationBean<UserFilter>();
		filterRegistrationBean.setFilter(new UserFilter(baseUser));
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	
	
}
