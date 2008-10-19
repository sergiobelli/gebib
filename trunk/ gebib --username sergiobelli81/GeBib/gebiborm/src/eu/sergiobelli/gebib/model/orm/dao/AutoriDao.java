package eu.sergiobelli.gebib.model.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.Autori;
import eu.sergiobelli.gebib.model.orm.data.HibernateUtil;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class AutoriDao implements IGeBibDao<Autori> {
	
	private static AutoriDao instance = null;
	private AutoriDao () { }
	public static AutoriDao getInstance () {
		
		if (instance == null) {
			instance = new AutoriDao();
		}
		
		return instance;
		
	}
	
	public void delete(int id) throws GeBibException {

		logger.info("Deleting autore " + id);
		Session session = null;
		Transaction transaction = null; 
		try {
			Autori autore = get(id);
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction(); 
			
			session.delete(autore);
			
			transaction.commit();
			
		} catch (Exception ex) {

			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante la cancellazione dell'autore ")
						.append(id)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		logger.info("Deleted autore " + id);

	}

	public Autori get(int id) throws GeBibException {

		logger.info("Getting autore " + id);
		Autori autore = null;
		Session session = null;
		try {
			
			session = HibernateUtil.currentSession();
			autore = (Autori) session.get(Autori.class, id);
			
		} catch (Exception ex) {
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante il reperimento dell'autore ")
						.append(id)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		logger.info("Getted autore " + id);
		
		return autore;
		
	}

//	public Autori get(Long[] ids) throws GeBibException {
//
//		logger.info("Getting autori " + ids);
//		Autori autore = null;
//		Session session = null;
//		try {
//			session = HibernateUtil.currentSession();
//			autore = (Autori) session.get(Autori.class, id);
//		} catch (Exception ex) {
//			
//			GeBibExceptionManager.manageException(
//					logger, 
//					new StringBuffer()
//					.append("Eccezione durante il reperimento dell'autore ")
//					.append(id)
//					.append(" ! ").toString(), 
//					ex);
//			throw new GeBibException(ex);
//			
//		} finally {
//			
//			if (Assert.isNotNull(session)) {
//				HibernateUtil.closeSession();				
//			}
//			
//		}
//		logger.info("Getted autore " + ids);
//		
//		return autore;
//		
//	}
	
	public Integer insert(Autori object) throws GeBibException {

		logger.info("Inserting autore " + object);
		
		Session session = null;
		Transaction transaction = null;
		Integer idAutore = null;
		try {
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			
			idAutore = (Integer) session.save(object);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante l'inserimento dell'autore ")
						.append(object)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
						
		}
		
		logger.info("Inserted autore " + object);
		return idAutore;
		
	}

	public List<Autori> list() throws GeBibException {
		
		List<Autori> autori = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
				
			Criteria criteria = session.createCriteria(Autori.class);
			criteria.addOrder(Order.asc("cognome"));
			
			autori = (List<Autori>) criteria.list();
//			autori = (List<Autori>) session.createQuery("from Autori autori order by autori.cognome").list();			
			
//			for (Autori autore : autori) {
//				Hibernate.initialize(autore.getLibri());	
//			}
			
		} catch (Exception ex) {

			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer().append("Eccezione durante il reperimento dell'elenco degli autori ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		return autori;
		
	}

	public void update(Autori object) throws GeBibException {

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			
			session.update(object);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante la modifica dell'autore ")
						.append(object)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		
	}

	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());
	
}
