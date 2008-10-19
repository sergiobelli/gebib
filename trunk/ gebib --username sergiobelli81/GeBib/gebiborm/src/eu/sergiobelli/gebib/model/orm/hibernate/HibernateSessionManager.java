package eu.sergiobelli.gebib.model.orm.hibernate;

import org.hibernate.Session;

import eu.sergiobelli.asserts.Assert;
import eu.sergiobelli.gebib.model.orm.data.HibernateUtil;

public class HibernateSessionManager {

	public static Session openSession() {
		return HibernateUtil.currentSession();
	}
	
	public static void closeSession() {
		
		if (Assert.isNotNull(HibernateUtil.currentSession())) {
			HibernateUtil.currentSession().flush();
		}
		
		HibernateUtil.closeSession();
	}
	
}
