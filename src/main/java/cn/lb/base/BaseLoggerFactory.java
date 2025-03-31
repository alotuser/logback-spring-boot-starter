package cn.lb.base;

/**
 * 日志工厂
 */
public class BaseLoggerFactory {
	/**
	 * 获取日志对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static BaseLogger getLogger(Class<?> clazz) {
		return new BaseLogger(clazz).proxyLogger(before->{},after->{});
	}

}
