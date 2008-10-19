package eu.sergiobelli.gebib.exception;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class GeBibExceptionManager {

	public static final void manageException (GeBibLogger logger, Throwable ex) {
		manageException (logger, "Exception = ", ex);
	}
	
	public static final void manageException (GeBibLogger logger, String message, Throwable ex) {
		
		if (Assert.isNotNull(ex)) {	
			if (ex instanceof GeBibException) {
				handleManagedException(logger, ex);
			} else {
				handleRawException(logger, ex);
			}			
		}	

	}

	public static void handleManagedException ( GeBibLogger logger, Throwable e ) {

		if (Assert.isNotNull(e)) {
			System.out.println("***** <GeComp> Catched exception : " + e.getClass().getName());
			System.out.println("***** <GeComp> message           : " + e.getMessage());
			
			if (Assert.isNotNull(logger)) {
				logger.error("***** <GeComp> Catched exception : " + e.getClass().getName());
				logger.error("***** <GeComp> message           : " + e.getMessage());
			}			
		}
		
	}

	public static void handleRawException ( GeBibLogger logger, Throwable e ) {

		if (Assert.isNotNull(e)) {

			System.out.println("*****************************************************************");
			System.out.println("***** <GeComp> Catched exception : " + e.getClass().getName());
			e.printStackTrace();
			System.out.println("*****************************************************************");
			
			if (Assert.isNotNull(logger)) {
				logger.error("*****************************************************************");
				logger.error("***** <GeComp> Catched exception : " + e.getClass().getName());
				logger.error("***** <GeComp> cause             : " + e.getCause());
				logger.error("***** <GeComp> message           : " + e.getMessage());
				logger.error("***** <GeComp> localized message : " + e.getLocalizedMessage());
				logger.error("***** <GeComp> trace             : ");
				for (StackTraceElement track : e.getStackTrace()) { logger.error(track); }
				logger.error("*****************************************************************");
			}
			
		}
		
	}
		
}
