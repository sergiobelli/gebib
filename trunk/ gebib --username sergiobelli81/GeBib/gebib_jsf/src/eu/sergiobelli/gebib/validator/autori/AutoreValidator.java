package eu.sergiobelli.gebib.validator.autori;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.model.orm.data.Autori;

public class AutoreValidator {

	protected static final ResourceBundle bundle = ResourceBundle.getBundle("eu.sergiobelli.gebib.validator.autori.messages");
	
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException { 
		validate(obj);
	}
	public void validate(Object obj) throws ValidatorException {

		if (Assert.isNull(obj)
				&& !(obj instanceof Autori)) {

			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, bundle
					.getString("autore.nonvalido"), ""));

		} else {
			
			Autori autore = (Autori)obj;
			new CognomeValidator().validate(autore.getCognome());
			new NomeValidator().validate(autore.getNome());
			
		}

	}

}