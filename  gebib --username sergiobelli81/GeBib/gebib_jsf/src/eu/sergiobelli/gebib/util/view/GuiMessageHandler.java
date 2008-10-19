package eu.sergiobelli.gebib.util.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import eu.sergiobelli.gebib.exception.MessageSeverity;

public class GuiMessageHandler {
	
	public static void addGUIMessage ( MessageSeverity severity, String message, String description ) {
		
		if (severity == MessageSeverity.info) {
			FacesContext.getCurrentInstance().addMessage ( null, new FacesMessage( FacesMessage.SEVERITY_INFO, message, description ) );
		} else if (severity == MessageSeverity.warn) {
			FacesContext.getCurrentInstance().addMessage ( null, new FacesMessage( FacesMessage.SEVERITY_WARN, message, description ) );
		} else if (severity == MessageSeverity.error) {
			FacesContext.getCurrentInstance().addMessage ( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, message, description ) );
		} else if (severity == MessageSeverity.fatal) {
			FacesContext.getCurrentInstance().addMessage ( null, new FacesMessage( FacesMessage.SEVERITY_FATAL, message, description ) );
		} else {
			FacesContext.getCurrentInstance().addMessage ( null, new FacesMessage( FacesMessage.SEVERITY_ERROR, message, description ) );
		}
		
	}
	
}
