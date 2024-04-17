package cn.test;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;

public class Test {

	
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Test.class);

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {

			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			// lc.reset();
			URL url = Loader.getResourceBySelfClassLoader("logback-userid.xml");
			configurator.doConfigure(url);
			
			lc.addTurboFilter(new TurboFilter() {
				
				
				
				
				@Override
				public FilterReply decide(Marker marker, ch.qos.logback.classic.Logger logger, Level level, String format,Object[] params, Throwable t) {

					return FilterReply.NEUTRAL;
				}
				
				
				@Override
				public void start() {
					System.out.println("start");
					super.start();
				}


				@Override
				public void stop() {
					System.out.println("stop");
					super.stop();
				}
				
				
			});
			
			
		} catch (JoranException je) {
			StatusPrinter.print(lc);
		}
		
		
		MDC.put("userId", "111");
		logger.debug("I know me " + 0);
		MDC.put("userId", "222");
		logger.debug("I know me " + 1);
		MDC.put("userId", "333");
		logger.debug("I know me " + 2);
	}

}
