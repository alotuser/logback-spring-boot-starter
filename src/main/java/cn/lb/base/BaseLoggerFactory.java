package cn.lb.base;

public class BaseLoggerFactory {

	public static BaseLogger getLogger(Class<?> clazz) {
		return new BaseLogger(clazz).proxyLogger(before->{},after->{});
	}

}
