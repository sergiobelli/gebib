package eu.sergiobelli.gebib.model.orm.data;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class HibernateUtil {
	
	private static final GeBibLogger logger = new GeBibLogger(HibernateUtil.class.getName());
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static final ThreadLocal session = new ThreadLocal();

	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}
	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null) {
			s.close();
		}
	}

	

}

