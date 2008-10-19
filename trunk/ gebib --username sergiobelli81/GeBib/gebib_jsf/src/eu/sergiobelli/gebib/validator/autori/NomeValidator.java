package eu.sergiobelli.gebib.validator.autori;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import eu.sergiobelli.asserts.Assert;

public class NomeValidator implements Validator {

	protected static final ResourceBundle bundle = ResourceBundle.getBundle("eu.sergiobelli.gebib.validator.autori.messages");
	
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		validate(obj);
	}
	public void validate(Object obj) throws ValidatorException {

		
		if (Assert.isNull(obj)
				|| (Assert.isEmpty(obj.toString()))) {
			
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, 
							bundle.getString("autore.nonvalido"), 
							bundle.getString("autore.nonvalido.nome.vuotoOnullo")));
			
		} else {
			String nome = obj.toString();
			
			if (nome.length() > 255) {
				
				throw new ValidatorException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, 
								bundle.getString("autore.nonvalido"), 
								bundle.getString("autore.nonvalido.nome.lunghezzaMassimaSuperata")));
				
			}
		}

	
	}

}
