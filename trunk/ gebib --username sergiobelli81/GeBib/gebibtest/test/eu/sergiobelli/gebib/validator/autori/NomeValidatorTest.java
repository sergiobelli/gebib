package test.eu.sergiobelli.gebib.validator.autori;

import javax.faces.validator.ValidatorException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.sergiobelli.gebib.validator.autori.NomeValidator;

public class NomeValidatorTest {

	private NomeValidator nomeValidator = null;
	
	@Before
	public void setUp() throws Exception {
		nomeValidator = new NomeValidator();
	}

	@After
	public void tearDown() throws Exception {
		nomeValidator = null;
	}

	@Test
	public final void testValidateOk() {
		
		nomeValidator.validate(null, null, "Ken");
		
	}
	
	@Test(expected=ValidatorException.class)
	public final void testValidateKo4Null() {
		
		nomeValidator.validate(null, null, null);
		
	}
	
	@Test(expected=ValidatorException.class)
	public final void testValidateKo4Empty() {
		
		nomeValidator.validate(null, null, "");		

	}
	
	@Test(expected=ValidatorException.class)
	public final void testValidateKo4Length() {

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 300; i++) {buf.append(".");}

		nomeValidator.validate(null, null, buf.toString());		

	}
}
