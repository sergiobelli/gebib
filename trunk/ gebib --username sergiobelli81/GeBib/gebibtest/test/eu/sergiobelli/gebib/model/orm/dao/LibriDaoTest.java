package test.eu.sergiobelli.gebib.model.orm.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.LibriDao;
import eu.sergiobelli.gebib.model.orm.data.Libri;

public class LibriDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public final void testGet() {
		
		System.out.println("<testGet>");
		try {
			
			Libri libro = LibriDao.getInstance().get(1);
			
			System.out.println(libro.getIdLibro());
			System.out.println(libro.getTitolo());
			System.out.println(libro.getIsbn());
			
			
			
		} catch (GeBibException e) {
			e.printStackTrace();
		}
		System.out.println("</testGet>");
		
	}

	@Test
	public final void testInsert() {
		
		System.out.println("<testInsert>");
		try {
			
			Libri libro = new Libri();
			libro.setTitolo("dummy_titolo");
			libro.setIsbn("dummy_isbn");
			Integer id = LibriDao.getInstance().insert(libro);
			System.out.println(id);
			
		} catch (Exception ex) {

			ex.printStackTrace();
			fail(ex.getMessage());
		
		}
		System.out.println("</testInsert>");
		
	}

	@Test
	public final void testList() {
		
		System.out.println("<testList>");
		try {
			List<Libri> libri = LibriDao.getInstance().list();
			
			for (Libri libro : libri) {
				System.out.println("");
				System.out.println(libro.getIdLibro());
				System.out.println(libro.getTitolo());
				System.out.println(libro.getIsbn());
				System.out.println("");
			}
			
			
		} catch (GeBibException e) {
			e.printStackTrace();
		}
		System.out.println("</testList>");
		
	}

	@Test
	public final void testUpdate() {
		System.out.println("<testUpdate>");

		try {
			
			Libri libro = new Libri();
			libro.setTitolo("dummy_titolo");
			libro.setIsbn("dummy_isbn");
			Integer id = LibriDao.getInstance().insert(libro);
			System.out.println(id);
			
			Libri updLibro = LibriDao.getInstance().get(id);
			updLibro.setIsbn("pippo");
			LibriDao.getInstance().update(updLibro);

		} catch (Exception ex) {

			ex.printStackTrace();
			fail(ex.getMessage());

		}
		System.out.println("</testUpdate>");

	}

	@Test
	public final void testDelete() {
		System.out.println("<testDelete>");

		try {
			
			Libri libro = new Libri();
			libro.setTitolo("dummy_titolo");
			libro.setIsbn("dummy_isbn");
			Integer id = LibriDao.getInstance().insert(libro);
			System.out.println(id);
			
			LibriDao.getInstance().delete(id);

		} catch (Exception ex) {

			ex.printStackTrace();
			fail(ex.getMessage());

		}
		System.out.println("</testDelete>");

	}
}
