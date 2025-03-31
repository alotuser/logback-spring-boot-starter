package cn.lb.config;

import java.net.URL;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.lb.base.BaseLogConfig;

/**
 * 日志配置
 */
@Configuration
@ConditionalOnClass({ LoggerFactory.class, JoranConfigurator.class })
public class LoggerConfig {

	@Value("${spring.profiles.active:dev}")
	private String activeProfiles;
	@Value("${lb.log.path:}")
	private String lblogPath;
	@Value("${lb.log.maxFileSize:10MB}")
	private String maxFileSize;
	@Value("${lb.log.maxHistory:30}")
	private String maxHistory;
	@Value("${lb.log.totalSizeCap:10GB}")
	private String totalSizeCap;
	@Value("${lb.log.isGzip:false}")
	private boolean isGzip;

	/**
	 * 日志配置
	 * 
	 * @return BaseLogConfig
	 */
	@Bean
	@ConditionalOnMissingBean
	BaseLogConfig logUserConfiguration() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {

			 
			
			String artifactId = FileNameUtil.mainName(FileUtil.getWebRoot().getName());
			
			lc.putProperty("artifactId", artifactId);
			lc.putProperty("springProfile", activeProfiles);
			lc.putProperty("APP_NAME", artifactId);
			lc.putProperty("APP_DIR", artifactId);
			lc.putProperty("MAX_HISTORY", maxHistory);
			lc.putProperty("TOTAL_SIZE_CAP", totalSizeCap);
			lc.putProperty("MAX_FILE_SIZE", maxFileSize);

			String bakPath = null;

			if (lblogPath != null&&lblogPath.length()>0) {
				bakPath=lblogPath+"/"+artifactId+"/user/bak/${userId}/all-log-%d{yyyy-MM-dd}.%i.log"+(isGzip?".gz":"");
				lc.putProperty("LOG_PATH", lblogPath);
				lc.putProperty("BAK_PATH", bakPath);
			}else {
				lblogPath = FileUtil.getWebRoot().getAbsolutePath() + "/logs/" + activeProfiles + "/";
				bakPath = lblogPath+artifactId+"/user/bak/${userId}/all-log-%d{yyyy-MM-dd}.%i.log"+(isGzip?".gz":"");
			    lc.putProperty("LOG_PATH", lblogPath);
			    lc.putProperty("BAK_PATH", bakPath);
            }
			
			StatusManager sm = lc.getStatusManager();
			if (sm != null) {
				sm.add(new StatusListener() {@Override public void addStatusEvent(Status status) {}});
			}
			
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			URL url = Loader.getResourceBySelfClassLoader("logback-userid.xml");
			configurator.doConfigure(url);
			
			
		} catch (Exception je) {
			StatusPrinter.print(lc);
		}

		return null;
	}

}
