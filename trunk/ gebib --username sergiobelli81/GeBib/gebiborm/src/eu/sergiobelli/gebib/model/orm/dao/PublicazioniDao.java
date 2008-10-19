package eu.sergiobelli.gebib.model.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.model.orm.data.HibernateUtil;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.model.orm.data.PublicazioniId;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;



public class PublicazioniDao {

	private static PublicazioniDao instance = null;
	private PublicazioniDao () { }
	public static PublicazioniDao getInstance () {
		
		if (instance == null) {
			instance = new PublicazioniDao();
		}
		
		return instance;
		
	}
	
	public void delete(PublicazioniId id) throws GeBibException {

		logger.info("Deleting publicazione " + id);
		
		Session session = null;
		Transaction transaction = null;
		try {
			
			Publicazioni publicazione = get(id);
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction(); 
			
			session.delete(publicazione);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante la cancellazione della publicazione ")
						.append(id)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		}
		logger.info("Deleted publicazione " + id);

	}

	public Publicazioni get(PublicazioniId id) throws GeBibException {

		logger.info("Getting publicazione " + id);
		Publicazioni publicazione = null;
		Session session = null;
		try {
			
			session = HibernateUtil.currentSession();
			publicazione = (Publicazioni) session.get(Publicazioni.class, id);
			
		} catch (Exception ex) {
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante il reperimento della publicazione ")
						.append(id)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		logger.info("Getted publicazione " + id);
		
		return publicazione;
		
	}

	public PublicazioniId insert(Publicazioni object) throws GeBibException {

		Session session = null;
		Transaction transaction = null;
		PublicazioniId publicazioniId = null;
		try {
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			
			publicazioniId = (PublicazioniId) session.save(object);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante l'inserimento della publicazione ")
						.append(object)
						.append(" ! ").toString(), 
					ex);
			throw new GeBibException(ex);
				
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		
		return publicazioniId;
		
	}

	public List<Publicazioni> list() throws GeBibException {
		
		List<Publicazioni> publicazioni = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
						
			publicazioni = (List<Publicazioni>) session.createQuery("from Publicazioni").list();			
			
		} catch (Exception ex) {
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer().append("Eccezione durante il reperimento dell'elenco delle publicazioni ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		return publicazioni;
		
	}
	public List<Publicazioni> list(int idLibro) throws GeBibException {
		
		List<Publicazioni> publicazioni = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
						
			Criteria criteria = session.createCriteria(Publicazioni.class);
			criteria.add(Restrictions.eq("id.idLibro",idLibro));
			
			publicazioni = (List<Publicazioni>) criteria.list();			
			
		} catch (Exception ex) {
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer().append("Eccezione durante il reperimento dell'elenco delle publicazioni ! ").toString(), 
					ex);
			throw new GeBibException(ex);
			
		} finally {
			
			if (Assert.isNotNull(session)) {
				HibernateUtil.closeSession();				
			}
			
		}
		return publicazioni;
		
	}
	public void update(Publicazioni object) throws GeBibException {

		Session session = null;
		Transaction transaction = null;
		try {
			
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			
			session.saveOrUpdate(object);
			
			transaction.commit();
			
		} catch (Exception ex) {
			
			transaction.rollback();
			
			GeBibExceptionManager.manageException(
					logger, 
					new StringBuffer()
						.append("Eccezione durante la modifica della publicazione ")
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