package eu.sergiobelli.gebib.control.autori;

import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;
import eu.sergiobelli.gebib.validator.autori.AutoreValidator;

public class InserisciAutoreExecuter {

	/**
	 * ddd
	 */
	public InserisciAutoreExecuter() {}
	
	private String cognome = "";
	public String getCognome() {return cognome;}
	public void setCognome(final String cognome) {this.cognome = cognome;}

	private String nome = "";
	public String getNome() {return nome;}
	public void setNome(final String nome) {this.nome = nome;}
	
	public String salva () {
		
		String redirect = "elencoAutori";
		try {
			
			
			
			final Autori autore = new Autori();
			autore.setCognome(cognome);
			autore.setNome(nome);
			new AutoreValidator().validate(autore);
			
			AutoriDao.getInstance().insert(autore);
			
		} catch (Exception ex) {
			
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
			redirect = "null";
			
		}
		
		return redirect;
	}
	
	protected static final GeBibLogger logger = new GeBibLogger(InserisciAutoreExecuter.class.getName());
	

}
