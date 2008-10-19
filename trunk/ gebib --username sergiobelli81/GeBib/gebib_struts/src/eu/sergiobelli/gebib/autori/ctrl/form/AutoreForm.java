package eu.sergiobelli.gebib.autori.ctrl.form;

import org.apache.struts.action.ActionForm;

public class AutoreForm extends ActionForm {

	private String cognome = "";
	public String getCognome() {return cognome;}
	public void setCognome(final String cognome) {this.cognome = cognome;}

	private String nome = "";
	public String getNome() {return nome;}
	public void setNome(final String nome) {this.nome = nome;}
	
}
