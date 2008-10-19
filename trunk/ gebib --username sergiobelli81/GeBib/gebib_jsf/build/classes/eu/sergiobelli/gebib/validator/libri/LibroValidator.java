package eu.sergiobelli.gebib.validator.libri;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class LibroValidator {

	protected static final ResourceBundle bundle = ResourceBundle.getBundle("eu.sergiobelli.gebib.validator.libri.messages");
	
	public void validateTitolo(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		
		try {
			validateTitolo(obj);
		} catch (Exception ex) {
			GeBibExceptionManager.manageException(logger, ex);
			throw (ValidatorException) ex;
		}
		
	}
	public void validateTitolo(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("libro.nonvalido"), 
							bundle.getString("libro.nonvalido.titolo.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 255) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("libro.nonvalido"), 
								bundle.getString("libro.nonvalido.titolo.lunghezzaMassimaSuperata")));
				
			}
		}

	}
	
	public void validateIsbn(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		
		try {
			validateIsbn(obj);
		} catch (Exception ex) {
			GeBibExceptionManager.manageException(logger, ex);
			throw (ValidatorException) ex;
		}
		
	}
	public void validateIsbn(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("libro.nonvalido"), 
							bundle.getString("libro.nonvalido.isbn.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 17) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("libro.nonvalido"), 
								bundle.getString("libro.nonvalido.isbn.lunghezzaMassimaSuperata")));
				
			}
		}

	}
	
	public void validate(Object obj) throws ValidatorException {

		if (Assert.isNull(obj)
				&& !(obj instanceof Libri)) {

			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, bundle
					.getString("libro.nonvalido"), ""));

		} else {
			
			Libri libro = (Libri)obj;
			validateTitolo(libro.getTitolo());
			validateIsbn(libro.getIsbn());
			
		}

	}
	
	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}