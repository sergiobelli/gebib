package eu.sergiobelli.gebib.util.logger;

import org.apache.log4j.Logger;

public class GeBibLogger {

	protected Logger logger = null;
	public GeBibLogger(String className) {
		this.logger = Logger.getLogger(className);
	}
	
	protected enum LogSeverity { log, debug, info, warn, error }
	protected void log(Object message, Throwable ex, LogSeverity logSeverity) {

		switch (logSeverity) {
		case log: {
			System.out.println(message);
			break;
		}
		case debug: {
			logger.debug(message);
			break;
		}
		case info: {
			logger.info(message);
			break;
		}
		case warn: {
			logger.warn(message);
			break;
		}
		case error: {
			if (ex == null) {
				logger.error(message);
			} else {
				logger.error(message, ex);
			}			
			break;
		}
		}
		
	}
	
	
	public final void log (Object message) { log(message, null, LogSeverity.log); }

	public final void debug (Object message) { log(message, null, LogSeverity.debug); }

	public final void info (Object message) { log(message, null, LogSeverity.info); }

	public final void warn (Object message) { log(message, null, LogSeverity.warn); }

	public final void error (Object message) { log(message, null, LogSeverity.error); }
	public final void error (Object message, Throwable ex) { log(message, ex, LogSeverity.error); }

}
