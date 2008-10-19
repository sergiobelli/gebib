package eu.sergiobelli.gebib.autori.ctrl.form;

import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class ModificaAutoreForm extends AutoreForm {
	
	protected static final GeBibLogger logger = new GeBibLogger(InserisciAutoreForm.class.getName());
	
	private String action;
	public String getAction() {return action;}
	public void setAction(String action) {this.action = action;}	

}
