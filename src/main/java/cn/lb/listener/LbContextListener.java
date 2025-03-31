package cn.lb.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;

/**
 * 日志上下文监听
 */
public class LbContextListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	public volatile Boolean isStarted = false;
	
	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	@Override
	public void start() {

		//synchronized (isStarted) {
			if (isStarted) {
				return;
			}
			String artifactId = FileNameUtil.mainName(FileUtil.getWebRoot().getName());
			Context context = getContext();
			StatusManager sm = getStatusManager();
			if (sm != null) {
				sm.add(new StatusListener() {@Override public void addStatusEvent(Status status) {}});
			}
			context.putProperty("artifactId", artifactId);
			isStarted = true;
		//}

	}
	
	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	@Override
	public void stop() {
		
		 isStarted = false;
	}

	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	@Override
	public boolean isStarted() {
		
		return isStarted;
	}

	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	@Override
	public boolean isResetResistant() {
		
		return false;
	}

	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	@Override
	public void onStart(LoggerContext context) {
		
		
	}
	
	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	
	@Override
	public void onReset(LoggerContext context) {
		
		
	}
	
	/**
	 * 是否已经启动
	 * 
	 * @return 是否已经启动
	 */
	@Override
	public void onStop(LoggerContext context) {
		
		
	}
	/**
	 * 是否已经启动
	 */
	@Override
	public void onLevelChange(Logger logger, Level level) {
		
		
	}

}
