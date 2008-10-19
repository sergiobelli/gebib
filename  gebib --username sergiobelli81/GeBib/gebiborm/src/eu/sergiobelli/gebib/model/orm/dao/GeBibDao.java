package eu.sergiobelli.gebib.model.orm.dao;

import java.io.Serializable;
import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;

public abstract class GeBibDao implements IGeBibDao {

	private static AutoriDao 		autoriDaoInstance = null;
	private static LibriDao 		libriDaoInstance = null;
	private static PropertiesDao 	propertiesDaoInstance = null;
	private GeBibDao () { }
//	public synchronized static LibriDao getInstance (GeBibTable table) {
//		
//		switch (table) {
//		case GeBibTable.autori: {
//			break;
//		}
//		}
//		
//		
//		if (instance == null) {
//			instance = new LibriDao();
//		}
//		
//		return instance;
//		
//	}
	
	public abstract void delete(int id) throws GeBibException;

	public abstract Serializable get(int id) throws GeBibException;

	public abstract Integer insert(Serializable object) throws GeBibException;

	public abstract List list() throws GeBibException;

	public abstract void update(Serializable object) throws GeBibException;

}
