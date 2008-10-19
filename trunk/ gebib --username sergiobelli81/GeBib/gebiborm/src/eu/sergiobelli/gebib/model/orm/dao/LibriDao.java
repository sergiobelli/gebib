package eu.sergiobelli.gebib.model.orm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.HibernateUtil;
import eu.sergiobelli.gebib.model.orm.data.Libri;
import eu.sergiobelli.gebib.model.orm.hibernate.HibernateSessionManager;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class LibriDao implements IGeBibDao<Libri> {

	private static LibriDao instance = null;
	private LibriDao () { }
	public synchronized static LibriDao getInstance () {
		
		if (instance == null) {
			instance = new LibriDao();
		}
		
		return instance;
		
	}
	
	public void delete(int id) throws GeBibException {

		logger.info("Deleting libro " + id);
		Session session = null;
		Transaction transaction = null; 
		try {

			Libri libro = get(id);
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction(); 
			
			session.delete(libro);
			
			transaction.commit();
			
		} catch (Exception ex) {

			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante la cancellazione del libro ")
						.append(id)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		logger.info("Deleted libro " + id);

	}

	public Libri get(final int id) throws GeBibException {

		logger.info("Getting libro " + id);
		Libri libro = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
			libro = (Libri) session.get(Libri.class, id);
		} catch (Exception ex) {
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante il reperimento del libro ")
						.append(id)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		logger.info("Getted libro " + id);
		
		return libro;
		
	}

	/**
	 * 
	 */
	public Integer insert(final Libri object) throws GeBibException {

		Session session = null;
		Transaction transaction = null;
		Integer idLibro = null;
		try {
			
			session = HibernateSessionManager.openSession();
			transaction = session.beginTransaction();
			
			idLibro = (Integer) session.save(object);
			
			transaction.commit();						
			
		} catch (Exception ex) {
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante l'inserimento del libro ")
						.append(object)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			HibernateSessionManager.closeSession();			
			
		}
		
		logger.info("Inserted libro " + object);
		return idLibro;
		
	}

	public List<Libri> list() throws GeBibException {
		
		List<Libri> libri = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
			
			
			libri = (List<Libri>) session.createQuery("from Libri libri order by libri.titolo").list();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GeBibException(ex);
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		return libri;
		
	}

	public void update(Libri object) throws GeBibException {

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
	
	private final GeBibLogger logger = new GeBibLogger(this.getClass().getName());

}
