package eu.sergiobelli.gebib.model.orm.dao;

import java.sql.SQLException;
import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.model.orm.data.IBatisSessionHandler;

import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class AutoriDao {



	private static AutoriDao instance;
	public static AutoriDao getInstance() {
		if (instance == null) {
			instance = new AutoriDao();
		}
		return instance;
	}
	private final String GET_AUTORI = "selectAutori";
	
	private final String LIST_AUTORI = "listAutori";
	private final String INSERT_AUTORI = "insertAutori";
	
	private final String UPDATE_AUTORI = "updateAutori";
	private final String DELETE_AUTORI = "deleteAutori";
	
	private AutoriDao() {}
	public void delete (int id) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().delete(DELETE_AUTORI, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	
	public Autori get (int id) throws GeBibException {
		try {
			return (Autori) IBatisSessionHandler.getInstance().getSqlMapClient().queryForObject(GET_AUTORI, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void insert (Autori autore) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().insert(INSERT_AUTORI, autore);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}

	public List<Autori> list () throws GeBibException {
		try {
			return (List<Autori>) IBatisSessionHandler.getInstance().getSqlMapClient().queryForList(LIST_AUTORI);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void update (Autori autore) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().update(UPDATE_AUTORI, autore);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}	

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());



}
