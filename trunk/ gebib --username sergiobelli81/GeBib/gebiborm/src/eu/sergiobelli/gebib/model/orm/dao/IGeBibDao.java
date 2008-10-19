package eu.sergiobelli.gebib.model.orm.dao;

import java.io.Serializable;
import java.util.List;

import eu.sergiobelli.gebib.exception.GeBibException;

/**
 * 
 * @author S.BELLI
 *
 * @param <T>
 */
public interface IGeBibDao<T extends Serializable> {

	/**
	 * 
	 * @param id
	 * @throws GeBibException
	 */
	void delete(final int id) throws GeBibException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws GeBibException
	 */
	T get(final int id) throws GeBibException;

	/**
	 * 
	 * @param object
	 * @throws GeBibException
	 */
	Integer insert(final T object) throws GeBibException;

	/**
	 * 
	 * @return
	 * @throws GeBibException
	 */
	List<T> list() throws GeBibException;

	/**
	 * 
	 * @param object
	 * @throws GeBibException
	 */
	void update(final T object) throws GeBibException;

}
