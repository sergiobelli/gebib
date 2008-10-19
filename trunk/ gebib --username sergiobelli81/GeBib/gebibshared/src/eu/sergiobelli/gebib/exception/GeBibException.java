package eu.sergiobelli.gebib.exception;

public class GeBibException extends java.lang.Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6979434820130789873L;
	
	public GeBibException() {}
	public GeBibException(final String message) { }
	public GeBibException(final Exception exception) { setStackTrace(exception.getStackTrace()); }
	
}