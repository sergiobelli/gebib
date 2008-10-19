package test.eu.sergiobelli.gebib.model.orm.dao;

import java.util.List;

import junit.framework.TestCase;
import eu.sergiobelli.gebib.exception.GeBibException;
import eu.sergiobelli.gebib.model.orm.dao.AutoriDao;
import eu.sergiobelli.gebib.model.orm.data.Autori;

public class AutoriDaoTest extends TestCase {

	public AutoriDaoTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {}

	protected void tearDown() throws Exception {}

	public final void testGetInstance() {
		assertTrue(AutoriDao.getInstance() instanceof AutoriDao);
	}

	public final void testGet() {

		System.out.println("<testGet>");
		try {
			Autori autore = AutoriDao.getInstance().get(3);
			System.out.println("");
			System.out.println(" "+autore.getCognome() + ", "+autore.getNome());
						
			System.out.println("");
		} catch (GeBibException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("</testGet>");
		
	}

	public final void testInsert() {
		
		System.out.println("<testInsert>");
		try {
			
			Autori autore = new Autori();
			autore.setCognome("dummy_cognome");
			autore.setNome("dummy_nome");
			Integer id = AutoriDao.getInstance().insert(autore);
			System.out.println(id);
			
		} catch (Exception ex) {

			ex.printStackTrace();
			fail(ex.getMessage());
		
		}
		System.out.println("</testInsert>");
		
	}

	public final void testList() {
		System.out.println("<testList>");
		
		try {
			
			List<Autori> autori = AutoriDao.getInstance().list();
			for (Autori autore : autori) {
				System.out.println("");
				System.out.println(" "+autore.getCognome() + ", "+autore.getNome());
		
				System.out.println("");
			}
			
			
		} catch (GeBibException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		System.out.println("</testList>");		
		
	}

	public final void testUpdate() {
		System.out.println("<testUpdate>");

		try {
			
			Autori autore = new Autori();
			autore.setCognome("dummy_cognome");
			autore.setNome("dummy_nome");
			Integer id = AutoriDao.getInstance().insert(autore);
			System.out.println(id);
			
			Autori updLibro = AutoriDao.getInstance().get(id);
			updLibro.setNome("pippo");
			AutoriDao.getInstance().update(updLibro);

		} catch (Exception ex) {

			ex.printStackTrace();
			fail(ex.getMessage());

		}
		System.out.println("</testUpdate>");

	}


	public final void testDelete() {
		System.out.println("<testDelete>");

		try {
			
			Autori autore = new Autori();
			autore.setCognome("dummy_cognome");
			autore.setNome("dummy_nome");
			Integer id = AutoriDao.getInstance().insert(autore);
			System.out.println(id);
			
			AutoriDao.getInstance().delete(id);

		} catch (Exception ex) {

			ex.printStackTrace();
			fail(ex.getMessage());

		}
		System.out.println("</testDelete>");

	}
	
}
