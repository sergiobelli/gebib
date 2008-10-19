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
import eu.sergiobelli.gebib.model.orm.dao.LibriDao;
import eu.sergiobelli.gebib.model.orm.data.Libri;

public class LibriDaoTest {

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
		assertTrue(LibriDao.getInstance() instanceof LibriDao);
	}

	@Test
	public final void testDelete() {
		try {

			List<Libri> libri = LibriDao.getInstance().list();
			
			LibriDao.getInstance().delete(libri.size());

		} catch (GeBibException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public final void testGet() {
		
		try {
			
			Libri libro = LibriDao.getInstance().get(1);
			System.out.println(libro);
			
		} catch (GeBibException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public final void testInsert() {
		try {
			Libri libro = new Libri();
			libro.setTitolo("pippo");
			libro.setIsbn("CCC");
			
			LibriDao.getInstance().insert(libro);
			
			

		} catch (GeBibException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public final void testList() {
		
		try {
			List<Libri> libri = LibriDao.getInstance().list();
			System.out.println(libri);
		} catch (GeBibException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public final void testUpdate() {
		
		try {
			
			Libri libro = new Libri();
			libro.setTitolo("pippo");
			libro.setIsbn("CCC");			
			LibriDao.getInstance().insert(libro);
			
			Libri newLibro = LibriDao.getInstance().get(1);
			newLibro.setTitolo("titolo");
			
			
			LibriDao.getInstance().update(newLibro);
			

		} catch (GeBibException e) {
			fail(e.getMessage());
		}
	}

}
