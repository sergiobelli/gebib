package eu.sergiobelli.gebib.control.properties;

import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.PropertiesDao;
import eu.sergiobelli.gebib.model.orm.data.Properties;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;

public class ElencoPropertiesExecuter {

	public ElencoPropertiesExecuter() {
		try {
			properties = PropertiesDao.getInstance().list();
		} catch (GeBibException ex) {
			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
		}
		
	}
	
	private List<Properties> properties = null;
	public List<Properties> getProperties() {
		return properties;
	}
	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}
	
	private final GeBibLogger logger = new GeBibLogger(this.getClass().getName());
	
}
