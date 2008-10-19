package eu.sergiobelli.gebib.model.orm.data;

public class Publicazioni implements java.io.Serializable {

	private Autori autore;
	public Autori getAutore() {return autore;}
	public void setAutore(Autori idAutore) {this.autore = idAutore;}
	
	private Libri libro;
	public Libri getLibro() {return libro;}
	public void setLibro(Libri libro) {this.libro = libro;}
	
}
