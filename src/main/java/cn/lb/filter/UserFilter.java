package cn.lb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

import cn.lb.base.BaseUser;
 
/**
 * 用户过滤器
 */
public class UserFilter implements Filter {

	BaseUser baseUser;

	/**
	 * 构造函数
	 * 
	 * @param baseUser
	 */
	public UserFilter(BaseUser baseUser) {
		this.baseUser=baseUser;
	}

	/**
	 * 构造函数
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	/**
	 * 过滤器
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isok = false;
		if (baseUser != null) {
			isok = registerUser(baseUser.getUserId());
		}

		try {
			chain.doFilter(request, response);
		} finally {
			if (isok) {
				MDC.remove(BaseUser.USER_ID);
			}
		}
	}

	/**
	 * 销毁
	 */
	@Override
	public void destroy() {

	}

	/**
	 * Register the user in the MDC under USER_KEY.
	 * 
	 * @param userId
	 * @return true id the user can be successfully registered
	 */
	private boolean registerUser(String userId) {
		if (userId != null && userId.trim().length() > 0) {
			MDC.put(BaseUser.USER_ID, userId);
			return true;
		}
		return false;
	}
}
