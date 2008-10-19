package eu.sergiobelli.gebib.control.libri;

import java.util.List;

import javax.faces.model.SelectItem;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.dao.LibriDao;
import eu.sergiobelli.gebib.model.orm.dao.PublicazioniDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.model.orm.data.PublicazioniId;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;
import eu.sergiobelli.gebib.validator.libri.LibroValidator;

public class InserisciLibroExecuter {

	protected int idLibro;
	public int getIdLibro() {return idLibro;}
	public void setIdLibro(int idLibro) {this.idLibro = idLibro;}
	
	protected String titolo;
	public String getTitolo() {return titolo;}
	public void setTitolo(String titolo) {this.titolo = titolo;}

	protected String isbn;
	public String getIsbn() {return isbn;}
	public void setIsbn(String isbn) {this.isbn = isbn;}
	
	protected Long[] autoriSelected;
	public Long[] getAutoriSelected() { return autoriSelected; }
	public void setAutoriSelected(Long[] autoriSelected) { this.autoriSelected = autoriSelected; }

	protected SelectItem[] autori;
	public SelectItem[] getAutori() { return autori; }
	public void setAutori(SelectItem[] autori) { this.autori = autori; }
	
	public InserisciLibroExecuter () {
		
		loadLibri();
		
	}
	
	protected void loadLibri() {
		
		try {
			
			List<Autori> autors = AutoriDao.getInstance().list();
			if (Assert.isNotNull(autors)) {
				autori = new SelectItem[autors.size()];
				int i = 0;
				for (Autori autor : autors) {
					autori[i] = new SelectItem(String.valueOf(autor.getIdAutore()), autor.getCognome()+", "+autor.getNome());
					i++;
				}	
			}
			
		} catch(Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}
	
	public String salva () {
		
		String redirect = "elencoLibri";
		try {
			Integer idLibro;
			Libri libro = new Libri();
			libro.setTitolo(titolo);
			libro.setIsbn(isbn);
			new LibroValidator().validate(libro);
			
			
			//Salvo il libro
			idLibro = LibriDao.getInstance().insert(libro);
			logger.info("Salvataggio del libro effettuato... identificativo generato = " + idLibro);
			
			//Salvo le publicazioni
			for (Long everyAuthor : autoriSelected) {
				PublicazioniId publicazioniId = PublicazioniDao.getInstance().insert(new Publicazioni(new PublicazioniId(everyAuthor.intValue(), idLibro)));
				logger.info("Salvataggio della publicazione effettuata... identificativo generato = " + publicazioniId);
			}

			
		} catch (Exception ex) {

			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);			
			redirect = "null";
			
		}
		
		return redirect;
	}
	
	private final GeBibLogger logger = new GeBibLogger(this.getClass().getName());
	
}
