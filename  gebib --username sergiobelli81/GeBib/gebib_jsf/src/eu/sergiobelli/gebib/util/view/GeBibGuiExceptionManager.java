package eu.sergiobelli.gebib.util.view;

import javax.faces.validator.ValidatorException;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.exception.MessageSeverity;
import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class GeBibGuiExceptionManager {
	
	public static final void manageGUIException (GeBibLogger logger, String message, Throwable ex) {
		
		if (Assert.isNotNull(ex)) {
			
			if (ex instanceof GeBibException) {
				GeBibExceptionManager.handleManagedException(logger, ex);
				GuiMessageHandler.addGUIMessage(MessageSeverity.error, ex.getMessage(), ex.getMessage());
			} else if (ex instanceof ValidatorException) {
				GeBibExceptionManager.handleManagedException(logger, ex);
				GuiMessageHandler.addGUIMessage(MessageSeverity.error, ex.getMessage(), ex.getMessage());
			} else {
				GeBibExceptionManager.handleRawException(logger, ex);
				GuiMessageHandler.addGUIMessage(MessageSeverity.error, message, ex.getMessage());
			}
			
			
			
		}	

	}


}
