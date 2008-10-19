package eu.sergiobelli.gebib.model.orm.dao;

import java.sql.SQLException;
import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.IBatisSessionHandler;
import eu.sergiobelli.gebib.model.orm.data.Properties;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;


public class PropertiesDao /*implements IGeBibDao<Properties>*/ {



	private static PropertiesDao instance;
	public static PropertiesDao getInstance() {
		if (instance == null) {
			instance = new PropertiesDao();
		}
		return instance;
	}
	private final String GET_PROPERTIES = "selectProperties";
	
	private final String LIST_PROPERTIES = "listProperties";
	private final String INSERT_PROPERTIES = "insertProperties";
	
	private final String UPDATE_PROPERTIES = "updateProperties";
	private final String DELETE_PROPERTIES = "deleteProperties";
	
	private PropertiesDao() {}
	public void delete (int id) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().delete(DELETE_PROPERTIES, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	
	public Properties get (int id) throws GeBibException {
		try {
			return (Properties) IBatisSessionHandler.getInstance().getSqlMapClient().queryForObject(GET_PROPERTIES, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void insert (Properties property) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().insert(INSERT_PROPERTIES, property);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}

	public List<Properties> list () throws GeBibException {
		try {
			return (List<Properties>) IBatisSessionHandler.getInstance().getSqlMapClient().queryForList(LIST_PROPERTIES);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void update (Properties property) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().update(UPDATE_PROPERTIES, property);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}	

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}
