package eu.sergiobelli.gebib.control.properties;

import eu.sergiobelli.gebib.model.orm.dao.PropertiesDao;
import eu.sergiobelli.gebib.model.orm.data.Properties;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;
import eu.sergiobelli.gebib.util.view.GeBibGuiExceptionManager;

public class InserisciPropertiesExecuter {

	private String key = "";
	public String getKey() {return key;}
	public void setKey(String key) {this.key = key;}

	private String value = "";
	public String getValue() {return value;}
	public void setValue(String nome) {this.value = nome;}
	
	public String salva () {
		
		String redirect = "elencoProperties";
		try {
			
			Properties properties = new Properties();
			properties.setChiave(key);
			properties.setValore(value);
			
			PropertiesDao.getInstance().insert(properties);
			
		} catch (Exception ex) {

			GeBibGuiExceptionManager.manageGUIException(logger, "Errore", ex);
			redirect = "null";
			
		}
		
		return redirect;
	}
	
	private final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}
