package eu.sergiobelli.gebib.control.autori;

import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;

public class ElencoAutoriExecuter {

// TODO: It is a good practice to call super() in a constructor
	public ElencoAutoriExecuter() {
		
		//super();
		
		try {			
			
			autori = AutoriDao.getInstance().list();
			logger.info(autori);
			
		} catch (GeBibException ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}
	
	private List<Autori> autori = null;
	public List<Autori> getAutori() {return autori;}
	public void setAutori(final List<Autori> autori) {this.autori = autori;}
	
	protected static final GeBibLogger logger = new GeBibLogger(ElencoAutoriExecuter.class.getName());

}
