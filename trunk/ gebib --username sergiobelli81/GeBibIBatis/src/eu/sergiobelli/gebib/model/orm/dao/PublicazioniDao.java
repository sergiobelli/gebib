package eu.sergiobelli.gebib.model.orm.dao;

import java.sql.SQLException;
import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.IBatisSessionHandler;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class PublicazioniDao {



	private static PublicazioniDao instance;
	public static PublicazioniDao getInstance() {
		if (instance == null) {
			instance = new PublicazioniDao();
		}
		return instance;
	}
	private final String GET_PUBLICAZIONI = "selectPublicazioni";
	
	private final String LIST_PUBLICAZIONI = "listPublicazioni";
	private final String INSERT_PUBLICAZIONI = "insertPublicazioni";
	
	private final String UPDATE_PUBLICAZIONI = "updatePublicazioni";
	private final String DELETE_PUBLICAZIONI = "deletePublicazioni";
	
	private PublicazioniDao() {}
	public void delete (int id) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().delete(DELETE_PUBLICAZIONI, id);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	
	public Publicazioni get (int idLibro, int idAutore) throws GeBibException {
		try {
			return (Publicazioni) IBatisSessionHandler.getInstance().getSqlMapClient().queryForObject(GET_PUBLICAZIONI, idLibro, idAutore);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void insert (Publicazioni autore) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().insert(INSERT_PUBLICAZIONI, autore);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}

	public List<Publicazioni> list () throws GeBibException {
		try {
			return (List<Publicazioni>) IBatisSessionHandler.getInstance().getSqlMapClient().queryForList(LIST_PUBLICAZIONI);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}
	public void update (Publicazioni autore) throws GeBibException {
		try {
			IBatisSessionHandler.getInstance().getSqlMapClient().update(UPDATE_PUBLICAZIONI, autore);
		} catch (SQLException e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new GeBibException(e.getMessage());
		}
	}	

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());





}
