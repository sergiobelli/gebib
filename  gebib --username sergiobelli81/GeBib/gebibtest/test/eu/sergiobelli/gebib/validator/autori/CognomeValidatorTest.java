package test.eu.sergiobelli.gebib.validator.autori;

import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.sergiobelli.gebib.validator.autori.CognomeValidator;

public class CognomeValidatorTest {

	private CognomeValidator cognomeValidator = null;
	
	@Before
	public void setUp() throws Exception {
		cognomeValidator = new CognomeValidator();
	}

	@After
	public void tearDown() throws Exception {
		cognomeValidator = null;
	}

	@Test
	public final void testValidateOk() {
		
		cognomeValidator.validate(null, null, "Follet");
		
	}
	
	@Test(expected=ValidatorException.class)
	public final void testValidateKo4Null() {
		
		cognomeValidator.validate(null, null, null);
		
	}
	
	@Test(expected=ValidatorException.class)
	public final void testValidateKo4Empty() {
		
		cognomeValidator.validate(null, null, "");		

	}
	
	@Test(expected=ValidatorException.class)
	public final void testValidateKo4Length() {

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 300; i++) {buf.append(".");}

		cognomeValidator.validate(null, null, buf.toString());		

	}
}
