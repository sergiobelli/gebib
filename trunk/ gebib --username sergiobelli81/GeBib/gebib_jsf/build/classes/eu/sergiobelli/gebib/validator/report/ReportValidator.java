package eu.sergiobelli.gebib.validator.report;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class ReportValidator {
	
	protected static final ResourceBundle bundle = ResourceBundle.getBundle("eu.sergiobelli.gebib.validator.report.messages");
	
	public void validateNomeCollezione(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		
		try {
			validateNomeCollezione(obj);
		} catch (Exception ex) {
			GeBibExceptionManager.manageException(logger, ex);
			throw (ValidatorException) ex;
		}
		
	}
	public void validateNomeCollezione(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("report.nonvalido"), 
							bundle.getString("report.nonvalido.nomeCollezione.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
//			if (cognome.length() > 17) {
//				
//				throw new ValidatorException(
//						new FacesMessage(
//								FacesMessage.SEVERITY_ERROR, 
//								bundle.getString("report.nonvalido"), 
//								bundle.getString("report.nonvalido.nomeCollezione.lunghezzaMassimaSuperata")));
//				
//			}
		}

	}
	
	public void validatePercorso(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		
		try {
			validatePercorso(obj);
		} catch (Exception ex) {
			GeBibExceptionManager.manageException(logger, ex);
			throw (ValidatorException) ex;
		}
		
	}
	public void validatePercorso(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("report.nonvalido"), 
							bundle.getString("report.nonvalido.percorso.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 255) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("report.nonvalido"), 
								bundle.getString("report.nonvalido.percorso.lunghezzaMassimaSuperata")));
				
			}
		}

	}
	
	public void validateNome(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		
		try {
			validateNome(obj);
		} catch (Exception ex) {
			GeBibExceptionManager.manageException(logger, ex);
			throw (ValidatorException) ex;
		}
		
	}
	public void validateNome(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("report.nonvalido"), 
							bundle.getString("report.nonvalido.nome.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 17) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("report.nonvalido"), 
								bundle.getString("report.nonvalido.nome.lunghezzaMassimaSuperata")));
				
			}
		}

	}
	
	public void validateEstensione(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		
		try {
			validateEstensione(obj);
		} catch (Exception ex) {
			GeBibExceptionManager.manageException(logger, ex);
			throw (ValidatorException) ex;
		}
		
	}
	public void validateEstensione(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("report.nonvalido"), 
							bundle.getString("report.nonvalido.estensione.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 17) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("report.nonvalido"), 
								bundle.getString("report.nonvalido.estensione.lunghezzaMassimaSuperata")));
				
			}
		}

	}	
	
	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}
