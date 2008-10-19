package test.eu.sergiobelli.gebib.model.orm.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

import eu.sergiobelli.gebib.model.orm.data.IBatisSessionHandler;

public class IBatisSessionHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetInstance() {
		assertTrue(IBatisSessionHandler.getInstance() instanceof IBatisSessionHandler);
	}

	@Test
	public final void testGetSqlMapClient() {
		assertTrue(IBatisSessionHandler.getInstance().getSqlMapClient() instanceof SqlMapClient);
	}

}
