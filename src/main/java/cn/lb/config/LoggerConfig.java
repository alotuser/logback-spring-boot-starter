package cn.lb.config;

import java.net.URL;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.turbo.MDCFilter;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;
import cn.lb.base.BaseLogConfig;
import cn.lb.base.BaseUser;


@Configuration
@ConditionalOnClass({LoggerFactory.class, JoranConfigurator.class})
public class LoggerConfig {


	@Bean
	@ConditionalOnMissingBean
	BaseLogConfig logUserConfiguration() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {

			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			// lc.reset();
			URL url = Loader.getResourceBySelfClassLoader("logback-userid.xml");
			configurator.doConfigure(url);
			
//			lc.addTurboFilter(new TurboFilter() {
//				@Override
//				public FilterReply decide(Marker marker, ch.qos.logback.classic.Logger logger, Level level, String format,Object[] params, Throwable t) {
//					baseUser.getUserId();
//					MDC.put(format, format);
//					
//					return FilterReply.NEUTRAL;
//				}
//			});
			
		} catch (JoranException je) {
			StatusPrinter.print(lc);
		}
		
		return null;
	}

}
