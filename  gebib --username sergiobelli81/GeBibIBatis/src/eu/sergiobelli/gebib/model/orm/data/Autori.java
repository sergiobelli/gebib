package eu.sergiobelli.gebib.model.orm.data;

public class Autori implements java.io.Serializable {

	private int idAutore;
	private String cognome;
	private String nome;

	public Autori() {
	}

	public Autori(int idAutore, String cognome, String nome) {
		this.idAutore = idAutore;
		this.cognome = cognome;
		this.nome = nome;
	}

	public int getIdAutore() {
		return this.idAutore;
	}

	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
