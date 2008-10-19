package eu.sergiobelli.gebib.control.autori;

import javax.faces.event.ActionEvent;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;

public class ModificaAutoreExecuter extends InserisciAutoreExecuter {
	
	private Integer idAutore;
	private Autori autore;
	private Autori getAutore() { return autore; }
	private void setAutore(Autori autore) { 
	
		this.autore = autore; 
	
		setCognome(autore.getCognome());
		setNome(autore.getNome());
	}
	
	/**
	 * 
	 */
	public ModificaAutoreExecuter () {
		
		//init ();		

	}
	
	public void load (ActionEvent event) {
		
		try {
			idAutore = (Integer) event.getComponent().getAttributes().get("idAutore");
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
		init ();
		
	}
	
	private void init () {
		
		try {
			
			setAutore(AutoriDao.getInstance().get(idAutore));
			
    } catch (GeBibException ex) {
    	GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante il caricamento dell'autore ! ", ex);
    }
    
	}
	
	
	public String modificaAutore () {
		return "modificaAutore";
	}

	public String eliminaAutore () {
		
		try {
			logger.info("Deleting Autore...");
			
			AutoriDao.getInstance().delete(idAutore);
			
			logger.info("Deleted Autore...");
//			
//			ElencoAutoriExecuter elencoAutoriExecuter = (ElencoAutoriExecuter) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("ElencoAutoriExecuter");
//			
//			if (Assert.isNotNull(elencoAutoriExecuter)) {
//				elencoAutoriExecuter = new ElencoAutoriExecuter();
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ElencoAutoriExecuter", elencoAutoriExecuter);
//			}
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante l'eliminazione dell'autore ! ", ex);
			return "null";
		}
		
		return "home";
	}
	
	/**
	 * 
	 * @return
	 */
	public String salva() {
		
		try {
			logger.info("Updating Autore...");
			
			Autori tmpAutore = retrieveAutore();
			logger.debug("Customized Autore = " + tmpAutore);
			
			AutoriDao.getInstance().update(tmpAutore);
			
			logger.info("Updated Autore...");
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante il salvataggio dell'autore ! ", ex);
			return "null";
		}			
		
		return "elencoAutori";
		
	}
	
	/**
	 * 
	 * @return
	 * @throws GeCompOrmException
	 */
	private Autori retrieveAutore() throws GeBibException {
		Autori tmpAtleta = new Autori();
				
		tmpAtleta.setIdAutore(autore.getIdAutore());
		tmpAtleta.setCognome(getCognome());
		tmpAtleta.setNome(getNome());
		
		return tmpAtleta;
	}

	private final GeBibLogger logger = new GeBibLogger(this.getClass().getName());
	
}
