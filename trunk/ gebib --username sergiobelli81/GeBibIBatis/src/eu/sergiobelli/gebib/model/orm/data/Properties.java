package eu.sergiobelli.gebib.model.orm.data;

public class Properties implements java.io.Serializable {

	private byte id;
	private String key;
	private String value;

	public Properties() {
	}

	public Properties(byte id, String key, String value) {
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
