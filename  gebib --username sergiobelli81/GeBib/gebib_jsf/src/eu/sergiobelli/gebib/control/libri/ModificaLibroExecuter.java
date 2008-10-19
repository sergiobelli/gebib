package eu.sergiobelli.gebib.control.libri;

import java.util.List;

import javax.faces.event.ActionEvent;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.dao.LibriDao;
import eu.sergiobelli.gebib.model.orm.dao.PublicazioniDao;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.model.orm.data.PublicazioniId;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;
import eu.sergiobelli.gebib.validator.libri.LibroValidator;

public class ModificaLibroExecuter extends InserisciLibroExecuter {
	
	private Libri libro;
	private Libri getLibro() { return libro; }
	private void setLibro(Libri libro) { 
	
		try {
			this.libro = libro; 			
			setIdLibro(libro.getIdLibro());
			setTitolo(libro.getTitolo());
			setIsbn(libro.getIsbn());

			List<Publicazioni> publicazioni = PublicazioniDao.getInstance().list(libro.getIdLibro());
			autoriSelected = new Long[publicazioni.size()];
			int i = 0;
			for (Publicazioni publicazione : publicazioni) {
				
				autoriSelected[i] = new Long(publicazione.getId().getIdAutore());
				
				i++;
			}
			
			loadLibri();
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante il caricamento del libro ! ", ex);
		}
	}
	
	/**
	 * 
	 */
	public ModificaLibroExecuter () {
		
		//init ();		

	}
	
	public void load (ActionEvent event) {
		
		try {
			idLibro = (Integer) event.getComponent().getAttributes().get("idLibro");
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante il caricamento del libro ! ", ex);
		}
		
		init ();
		
	}
	
	private void init () {
		
		try {
			
			setLibro(LibriDao.getInstance().get(idLibro));
			
    } catch (GeBibException ex) {
    	GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante il caricamento del libro ! ", ex);
    }
    
	}
	
	
	public String modificaLibro () {
		return "modificaLibro";
	}

	public String eliminaLibro () {
		
		try {
			logger.info("Deleting Libro...");
			
			LibriDao.getInstance().delete(idLibro);
			
			logger.info("Deleted Libro...");
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante l'eliminazione del libro ! ", ex);
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
			logger.info("Updating Libro...");
			
			Libri tmpLibro = retrieveLibro();
			logger.debug("Customized Libro = " + tmpLibro);
			new LibroValidator().validate(tmpLibro);
			
			LibriDao.getInstance().update(tmpLibro);
			
			//Elimino vecchie publicazioni del libro
			for (Publicazioni publicazione : PublicazioniDao.getInstance().list(tmpLibro.getIdLibro())) {
				PublicazioniDao.getInstance().delete(publicazione.getId());
			}
			
			
			//Salvo le publicazioni
			for (Long everyAuthor : autoriSelected) {
				PublicazioniDao.getInstance().update(new Publicazioni(new PublicazioniId(everyAuthor.intValue(), idLibro)));				
			}
			
			logger.info("Updated Libro...");
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore durante il salvataggio del libro ! ", ex);
			return "null";
		}			
		
		return "elencoLibri";
		
	}
	
	/**
	 * 
	 * @return
	 * @throws GeCompOrmException
	 */
	private Libri retrieveLibro() throws GeBibException {
		Libri tmpLibro = new Libri();
				
		tmpLibro.setIdLibro(idLibro);
		tmpLibro.setTitolo(titolo);
		tmpLibro.setIsbn(isbn);
		
		return tmpLibro;
	}

	private final GeBibLogger logger = new GeBibLogger(this.getClass().getName());
	


}
