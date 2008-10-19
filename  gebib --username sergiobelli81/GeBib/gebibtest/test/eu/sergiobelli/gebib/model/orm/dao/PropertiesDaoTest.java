package test.eu.sergiobelli.gebib.model.orm.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.PropertiesDao;
import eu.sergiobelli.gebib.model.orm.data.Properties;

public class PropertiesDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetInstance() {
		assertTrue(PropertiesDao.getInstance() instanceof PropertiesDao);
	}

	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGet() {

		try {
			String property = PropertiesDao.getInstance().get("gebib.collection.name");
			System.out.println(property);
		} catch (GeBibException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public final void testInsert() {
		
		try {

			Properties properties = new Properties();
			properties.setChiave("key2");
			properties.setValore("value");

			PropertiesDao.getInstance().insert(properties);

		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
	}

	@Test
	public final void testList() {

		try {
			List<Properties> properties = PropertiesDao.getInstance().list();
			System.out.println(properties);
		} catch (GeBibException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

}
