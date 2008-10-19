package test.eu.sergiobelli.gebib.model.orm.dao;

import junit.framework.Test;
import junit.framework.TestSuite;

public class GeBibDaoSuite {

	public static Test suite() {
		final TestSuite suite = new TestSuite("Test for test.eu.sergiobelli.gebib.model.orm.dao");
		
		suite.addTestSuite(AutoriDaoTest.class);
		
		return suite;
	}

}