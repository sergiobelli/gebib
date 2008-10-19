package eu.sergiobelli.gebib.model.orm.data;

public class Libri implements java.io.Serializable {

	private int idLibro;
	private String titolo;
	private String isbn;

	public Libri() {
	}

	public Libri(int idLibro, String titolo) {
		this.idLibro = idLibro;
		this.titolo = titolo;
	}

	public Libri(int idLibro, String titolo, String isbn) {
		this.idLibro = idLibro;
		this.titolo = titolo;
		this.isbn = isbn;
	}

	public int getIdLibro() {
		return this.idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
