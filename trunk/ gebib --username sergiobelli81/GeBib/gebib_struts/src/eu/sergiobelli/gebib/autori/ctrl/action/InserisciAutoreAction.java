package eu.sergiobelli.gebib.autori.ctrl.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import eu.sergiobelli.gebib.autori.ctrl.form.InserisciAutoreForm;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

/**
 * 
 * @author S.BELLI
 *
 */
public class InserisciAutoreAction extends Action {
	 
	protected static final GeBibLogger logger = new GeBibLogger(InserisciAutoreAction.class.getName());
	
	public ActionForward perform(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		//HttpSession
		HttpSession session = request.getSession();
		
		//The error vector 
		ActionErrors errors = new ActionErrors();

		// The validation form
		InserisciAutoreForm inserisciAutoreForm = (InserisciAutoreForm) form;

	    // the action we have to perform
	    String action = inserisciAutoreForm.getAction() ;
    
		return mapping.findForward("");
		
	}
	
}
