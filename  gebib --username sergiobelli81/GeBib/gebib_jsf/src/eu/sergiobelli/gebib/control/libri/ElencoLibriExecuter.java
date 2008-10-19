package eu.sergiobelli.gebib.control.libri;

import java.util.ArrayList;
import java.util.List;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.dao.LibriDao;
import eu.sergiobelli.gebib.model.orm.dao.PropertiesDao;
import eu.sergiobelli.gebib.model.orm.dao.PublicazioniDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;

public class ElencoLibriExecuter {

	protected List<Libro4Elenco> libri = null;
	public List<Libro4Elenco> getLibri() {return libri;}
	public void setLibri(List<Libro4Elenco> autori) {this.libri = autori;}
	
	public ElencoLibriExecuter() {
		try {
			List<Libri> list = LibriDao.getInstance().list();
			if (Assert.isNotNull(list)) {
				libri = new ArrayList<Libro4Elenco>();
				for (Libri libro : list) {
					
					Libro4Elenco libro4Elenco = new Libro4Elenco();
					libro4Elenco.setIdLibro(libro.getIdLibro());
					libro4Elenco.setTitolo(libro.getTitolo());
					libro4Elenco.setIsbn(libro.getIsbn());
					
					StringBuffer listaAutori = new StringBuffer("");
					
					String listSeparator = PropertiesDao.getInstance().get("gebib.list.separator");
					List<Publicazioni> publicazioni = PublicazioniDao.getInstance().list(libro.getIdLibro());
					
					for (Publicazioni publicazione : publicazioni) {
						Autori autore = AutoriDao.getInstance().get(publicazione.getId().getIdAutore());
						listaAutori
							.append(autore.getCognome())
							.append(", ")
							.append(autore.getNome())
							.append(listSeparator);
					}
					
					String str = 
						(listaAutori.toString().length() != 0) ? 
								listaAutori.toString().substring(0, listaAutori.toString().length() - 1) : 
									"";
					libro4Elenco.setListaAutori(str);
					
					libri.add(libro4Elenco);
					
				}
			}
			
		} catch (Exception ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}	
	
	public class Libro4Elenco {
		
		private int idLibro;
		private String titolo;
		private String isbn;
		private String listaAutori;
		public int getIdLibro() {return idLibro;}
		public void setIdLibro(int idLibro) {this.idLibro = idLibro;}
		public String getTitolo() {return titolo;}
		public void setTitolo(String titolo) {this.titolo = titolo;}
		public String getIsbn() {return isbn;}
		public void setIsbn(String isbn) {this.isbn = isbn;}
		public String getListaAutori() {return listaAutori;}
		public void setListaAutori(String listaAutori) {this.listaAutori = listaAutori;}
		
	}
	
	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}
