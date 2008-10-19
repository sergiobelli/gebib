package eu.sergiobelli.gebib.model.orm.dao;

import java.sql.SQLException;
import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.IBatisSessionHandler;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class LibriDao {



	private static LibriDao instance;
	public static LibriDao getInstance() {
		if (instance == null) {
			instance = new LibriDao();
		}
		return instance;
	}
	private final String GET_LIBRI = "selectLibri";
	
	private final String LIST_LIBRI = "listLibri";
	private final String INSERT_LIBRI = "insertLibri";
	
	private final String UPDATE_LIBRI = "updateLibri";
	private final String DELETE_LIBRI = "deleteLibri";
	
	private LibriDao() {}
	public void delete (int id) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().delete(DELETE_LIBRI, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	
	public Libri get (int id) throws GeBibException {
		try {
			return (Libri) IBatisSessionHandler.getInstance().getSqlMapClient().queryForObject(GET_LIBRI, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void insert (Libri libro) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().insert(INSERT_LIBRI, libro);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}

	public List<Libri> list () throws GeBibException {
		try {
			return (List<Libri>) IBatisSessionHandler.getInstance().getSqlMapClient().queryForList(LIST_LIBRI);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void update (Libri libro) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().update(UPDATE_LIBRI, libro);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}	

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());





}
