package eu.sergiobelli.gebib.validator.properties;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import eu.sergiobelli.asserts.Assert;

public class PropertiesValidator {

	protected static final ResourceBundle bundle = ResourceBundle.getBundle("eu.sergiobelli.gebib.validator.properties.messages");
	
	public void validateKey(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		validateKey(obj);
	}
	public void validateKey(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("properties.nonvalido"), 
							bundle.getString("properties.nonvalido.key.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 255) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("properties.nonvalido"), 
								bundle.getString("properties.nonvalido.key.lunghezzaMassimaSuperata")));
				
			}
		}

	}
	
	public void validateValue(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		validateValue(obj);
	}
	public void validateValue(Object obj) throws ValidatorException {
				
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("properties.nonvalido"), 
							bundle.getString("properties.nonvalido.value.vuotoOnullo")));
			
		} else {
			String cognome = obj.toString();
			
			if (cognome.length() > 255) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("properties.nonvalido"), 
								bundle.getString("properties.nonvalido.value.lunghezzaMassimaSuperata")));
				
			}
		}

	}
}
