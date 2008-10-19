package test.eu.sergiobelli.gebib.model.orm.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.PublicazioniDao;
import eu.sergiobelli.gebib.model.orm.data.Publicazioni;
import eu.sergiobelli.gebib.model.orm.data.PublicazioniId;

public class PublicazioniDaoTest {

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
		assertTrue(PublicazioniDao.getInstance() instanceof PublicazioniDao);
	}

	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGet() {

		try {
			
			Publicazioni publicazione = PublicazioniDao.getInstance().get(new PublicazioniId(1,1));
			
			System.out.println("");
			System.out.println(" "+publicazione.getId().getIdAutore() + ", "+publicazione.getId().getIdLibro());
			System.out.println("");
			
		} catch (GeBibException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public final void testInsert() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testList() {
		
		try {
			
			List<Publicazioni> publicazioni = PublicazioniDao.getInstance().list();
			for (Publicazioni publicazione : publicazioni) {
				System.out.println("");
				System.out.println(" "+publicazione.getId().getIdAutore() + ", "+publicazione.getId().getIdLibro());
				System.out.println("");
			}
			
			
		} catch (GeBibException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public final void testListWithIdLibro() {
		
		try {
			
			List<Publicazioni> publicazioni = PublicazioniDao.getInstance().list(15);
			for (Publicazioni publicazione : publicazioni) {
				System.out.println("");
				System.out.println(" "+publicazione.getId().getIdAutore() + ", "+publicazione.getId().getIdLibro());
				System.out.println("");
			}
			
			
		} catch (GeBibException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public final void testListInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

}
