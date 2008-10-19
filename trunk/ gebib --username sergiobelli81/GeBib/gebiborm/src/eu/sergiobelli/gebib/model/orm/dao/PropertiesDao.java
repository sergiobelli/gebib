package eu.sergiobelli.gebib.model.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.data.HibernateUtil;
import eu.sergiobelli.gebib.model.orm.data.Properties;
import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;


public class PropertiesDao implements IGeBibDao<Properties> {

	private static PropertiesDao instance = null;
	private PropertiesDao () { }
	public static PropertiesDao getInstance () {
		
		if (instance == null) {
			instance = new PropertiesDao();
		}
		
		return instance;
		
	}
	
	public void delete(int id) throws GeBibException {

		logger.info("Deleting property " + id);
		try {
			HibernateUtil.currentSession().delete(get(id));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			throw new GeBibException(ex);
		}
		logger.info("Deleted property " + id);

	}

	public Properties get(int id) throws GeBibException {

		logger.info("Getting property " + id);
		Properties property = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
			property = (Properties) session.get(Properties.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			throw new GeBibException(ex);
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		logger.info("Getted property " + id);
		
		return property;
		
	}
	public String get(String chiave) throws GeBibException {
		  for (Properties property : list()) {
		  	if (property.getChiave().equals(chiave)) {
		  		return property.getValore();
		  	}
		  }
		  return null;
	}

	public Integer insert(Properties object) throws GeBibException {

		Session session = null;
		Transaction transaction = null;
		Integer idProperties = null;
		try {
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			
			/*idProperties = (Integer) */session.save(object);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			ex.printStackTrace();
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		
		return idProperties;
	}

	public List<Properties> list() throws GeBibException {
		
		List<Properties> properties = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
						
			Criteria c = session.createCriteria(Properties.class);
			c.addOrder(Order.asc("chiave"));
			properties = (List<Properties>) c.list();			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GeBibException(ex);
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		return properties;
		
	}

	public void update(Properties object) throws GeBibException {

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			
			session.update(object);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			ex.printStackTrace();
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		
	}

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}
